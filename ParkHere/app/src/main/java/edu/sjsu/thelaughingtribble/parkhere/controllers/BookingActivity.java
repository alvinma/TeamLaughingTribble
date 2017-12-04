package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.app.DatePickerDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.PostHistory;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renting;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

public class BookingActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private User user;
    private Post post;
    private Button purchaseButton;

    private TextView postTitle;
    private ImageView postImage;
    private TextView postOwner;
    private TextView datePosted;
    private TextView postPrice;
    private TextView postDescription;
    private TextView startDate;
    private TextView endDate;

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
                post = (Post) extras.getSerializable("Post.class");
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
        startDate = (TextView) findViewById(R.id.start_date);
        endDate = (TextView) findViewById(R.id.end_date);
    }

    public void updateLabel(){
        endDate.setText("end Time");
    }

    public Boolean openSlot(String startDate, String endDate){
        //startDate is later than endDate
        if(startDate.compareTo(endDate) > 0){
            return false;
        }

        String booked_start;
        String booked_end;
        for(Renting booking: postHistory.getHistory()){
            booked_start = booking.getStartDate();
            booked_end = booking.getEndDate();

            //TODO: haven't testing this function but the logic is there
            //startDate is earlier than booked_Start
            if(startDate.compareTo(booked_start) < 0){
                //endDate is later than booked_start
                if(endDate.compareTo(booked_start) > 0){
                    return false;
                }
            }else{
                if(startDate.compareTo(booked_end) < 0){
                    return false;
                }
            }

            //booked_start=====booked_end
            //b_s, s+d, s+e, b_e
            //b_s, s+d, b_e, s+e

            //s+d, b_s, b_e, s+e
            //s+d, b_s, s+e, b_e
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

                //tests the avaliability of the spot
                if(!openSlot(startDate.getText().toString(), endDate.getText().toString())){
                    return;
                }

                Snackbar.make(view, "Purchasing the item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String key = mReference.child(user.getUid()).push().getKey();
                //Adding values to purchase History
                //GET HISTORY //TODO: GETHISITOYR


                String spotID = post.getSpot().getFirebaseKey();
                mReference = mDatabase.getReference(Constant.POST_HISTORY + "/" + spotID);


//                mReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot item : dataSnapshot.getChildren()){
//                            Renting booking = item.getValue(Renting.class);
//                            postHistory.addToHistory(booking);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                Spot spot = post.getSpot();

                String startDate = "START";
                String endDate = "END";

                Renter user_renting = (Renter) user;
                Renting booking = new Renting(spot, user_renting, post.getOwner(), startDate, endDate);
                booking.setOwner(post.getOwner());
                booking.setRenter(user_renting);

                booking.setFirebaseKey(mDatabase.getReference().getKey()); //TODO: might not be the way to get the keyID

                String bookingID = booking.getFirebaseKey();
                mReference.child("postHistory/" + spotID + "/" + bookingID).setValue(booking);
            }
        });
    }

    public void updateUI(){
        if(post.getSpot() != null){
            postDescription.setText("Description: " + post.getSpot().getDescription());
            postPrice.setText(String.valueOf(post.getSpot().getPrice()));
        }else{
            postDescription.setText("Description: Empty Value");
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
