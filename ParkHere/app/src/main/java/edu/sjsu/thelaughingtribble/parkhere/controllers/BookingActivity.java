package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.PostHistory;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renting;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

public class BookingActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private Renter renter;
    private User user;
    private Post post;
    private Button purchaseButton;

    private TextView postTitle;
    private ImageView postImage;
    private TextView postOwner;
    private TextView datePosted;
    private TextView postPrice;
    private TextView postDescription;
    private static TextView startDate;
    private static TextView endDate;

    PostHistory postHistory;
    private Boolean FLAG_DATES_PICKED = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Review Booking Info:");
        setSupportActionBar(toolbar);

        extractBundle(savedInstanceState);
        init();
        updateUI();
    }

    public void extractBundle(Bundle savedInstanceState){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                post = (Post) extras.getSerializable(Constant.INTENT_EXTRA_POST);
                renter = (Renter) extras.getSerializable(Constant.INTENT_EXTRA_USER);
                user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
                renter = new Renter(user);
            }
            catch(Exception ex){
                Toast.makeText(getBaseContext(), "Error Getting class..", Toast.LENGTH_SHORT);
            }
        }

        postImage = (ImageView) findViewById(R.id.post_image_info);
        postTitle = (TextView) findViewById(R.id.post_location_info);
        postPrice = (TextView) findViewById(R.id.post_price_info);
        postOwner = (TextView) findViewById(R.id.post_owner_name);
//        datePosted = (TextView) findViewById(R.id.post_date_posted);
        postDescription = (TextView) findViewById(R.id.post_description_info);
        purchaseButton = (Button) findViewById(R.id.purchaseButton);
//        startDate = (TextView) findViewById(R.id.start_date);
//        endDate = (TextView) findViewById(R.id.end_date);
        //TODO: editing the startDate and endDate
    }

    public void updateLabel(){
        endDate.setText("end Time");
    }

    //Return false if the given times are not avaliable
    public Boolean openSlot(String startDate, String endDate){
        Date startDate_f = Utilities.convertStringDate(startDate);
        Date endDate_f = Utilities.convertStringDate(endDate);

        //startDate is later than endDate
        if(startDate_f.after(endDate_f)){
            Toast.makeText(getBaseContext(), "'Start Date' cannot be AFTER 'End Date'", Toast.LENGTH_LONG).show();
            return false;
        }

        Date booked_start;
        Date booked_end;

        for(Renting booking: postHistory.getHistory()){
            booked_start = Utilities.convertStringDate(booking.getStartDate());
            booked_end = Utilities.convertStringDate(booking.getEndDate());

            //TODO: haven't testing this function but the logic is there
            //startDate is earlier than booked_Start
            if(startDate_f.before(booked_start)){
                //endDate is later than booked_start
                if(endDate_f.after(booked_start)){
                    return false;
                }
            }else{
                if(startDate_f.after(booked_end)){
                    return false;
                }
            }
        }
        return true;
    }

    public void init(){
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle("Start Date:");
                new DatePickerDialog(BookingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FLAG_DATES_PICKED){
                    return;
                }

                String spotID = post.getSpot().getFirebaseKey();
                mReference = mDatabase.getReference(Constant.POST_HISTORY + "/" + spotID);
                //Moved to subtree from "~/" to "~/postHistory/spotID/bookingID"
                mReference = mReference.child(user.getUid());
                String key = mReference.push().getKey();

                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot item : dataSnapshot.getChildren()){
                            Renting booking = item.getValue(Renting.class);
                            postHistory.addToHistory(booking);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //tests the avaliability of the spot
                if(!openSlot(startDate.getText().toString(), endDate.getText().toString())){
                    //Cancel the booking if the time is not avaliable
                    return;
                }

                Snackbar.make(view, "Purchasing the item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Spot spot = post.getSpot();

                String startDate_i = startDate.getText().toString();    //"Start Date"
                String endDate_i = endDate.getText().toString();        //"End Date"

                Renter user_renting = (Renter) user;
                Renting booking = new Renting(spot, user_renting, post.getOwner(), startDate_i, endDate_i);
                booking.setOwner(post.getOwner());
                booking.setRenter(user_renting);

                booking.setFirebaseKey(mDatabase.getReference().push().getKey()); //TODO: might not be the way to get the keyID

                String bookingID = booking.getFirebaseKey();
                mReference.child("postHistory/" + spotID + "/" + bookingID).setValue(booking);

                //Go back to main
                MainActivity.startIntent(getBaseContext(), user);
            }
        });
    }

    public void updateUI(){
        if(post.getSpot() != null){
            postDescription.setText("Description: " + post.getSpot().getDescription());
            postPrice.setText(String.valueOf(post.getSpot().getPrice()));
        }else{
            postDescription.setText("Description: Empty Value");
            postPrice.setText("Price: Missing");
        }

        if(post.getOwner() != null){
            postOwner.setText(post.getOwner().getFirstName());
        }else{
            postOwner.setText("Owner: Empty Value");
        }

        if(post.getTitle() != null) {
            postTitle.setText(post.getTitle());
        }else{
            postTitle.setText("Empty Value");
        }


        if (post.getSpot().getPhoto() != null) {
            Glide.with(getBaseContext()).load(post.getSpot().getPhoto()).into(postImage);
        } else {
            postImage.setImageResource(R.drawable.not_available);
        }
    }
}
