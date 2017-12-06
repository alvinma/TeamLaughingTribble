package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.RateAndComment;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.PostHistory;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renting;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.PostDetailActivityViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.SpotDetailViewModel;

import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.start;
import static edu.sjsu.thelaughingtribble.parkhere.Utils.Constant.POSTING;

public class PostDetailActivity extends AppCompatActivity {

    private boolean posting;
    private User user;
    private Renter renter;
    private Spot spot;
    private Place place;
    private Post post;
    private String title;
    private static PostDetailActivityViewModel postDetailUI;
    private String placeId;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private PostHistory postHistory;

    private static boolean FLAG_DATE_START = false;
    private static boolean FLAG_DATE_END = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity_detail);
        init();
        Log.i("title", title);
        Log.i("placeid", placeId);
        Log.i("posting", ""+posting);
        Log.i("spot", spot.getSpotId());
    }

    private void init(){
        database = FirebaseDatabase.getInstance();
        postHistory = new PostHistory();
        postHistory.setHistory(new ArrayList<Renting>());
        getDataFromIntent();
        setUpUI();

    }
    private boolean isDebugging(){
        boolean debugging = false;
        try {
            debugging = getIntent().getExtras().getBoolean(Constant.DEBUGGING);
        }
        catch (Exception ex){
            //Value not set
        }
        if(debugging){
            post = (Post) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_POST);
            user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
//            renter = (Renter) user;
            renter = new Renter(user);
            spot = post.getSpot();
            placeId = post.getPlaceID();
            title = post.getTitle();
            return true;
        }
        return false;
    }
    private void getDataFromIntent(){
        posting = getIntent().getExtras().getBoolean(Constant.POSTING);
        if(isDebugging()){
            //getting the info in the method
            return;
        }
        else if(posting){
            user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
            spot = (Spot) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_SPOT);
            placeId = getIntent().getExtras().getString(Constant.PLACEID);
            title = getIntent().getExtras().getString(Constant.TITLE);

        }else {
            user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
            post = (Post) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_POST);
            title = post.getTitle();
        }
    }

    private void submitPost(){
        String postId = database.getReference().child("post").child(user.getUid()).push().getKey();
        post = new Post(placeId, spot.getSpotId(), user.getUid(), title);
        database.getReference().child("post/" + postId).setValue(post);
        MainActivity.startIntent(getBaseContext(), user);
    }

    public void viewComments(View view){
        Toast.makeText(view.getContext(), "Loading Comments...", Toast.LENGTH_SHORT).show();

        if(false){
            //TODO: remove if() & add Alvins Comment Class
            Intent intent = new Intent(view.getContext(), RateAndComment.class);
            intent.putExtra(Constant.INTENT_EXTRA_SPOT, spot);
            intent.putExtra(Constant.INTENT_EXTRA_USER, user);
            view.getContext().startActivity(intent);
        }
    }

    //Edited user ->renter
    private void bookPost(View view){
        if(false){
            Toast.makeText(getApplicationContext(), "Booking...", Toast.LENGTH_SHORT).show();
            //database.getReference().child("postHistory/" + spot.getFirebaseKey() + "/" + postId).setValue(booking);
        }
        else if(!FLAG_DATE_START || !FLAG_DATE_END
                || postDetailUI.getStartDate().getText().toString().equals("startDate_text")
                || postDetailUI.getEndDate().getText().toString().equals("endDate_text")){
            Toast.makeText(getApplicationContext(), "Invalid Dates", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            String spotID = post.getSpot().getFirebaseKey();
            reference = database.getReference(Constant.POST_HISTORY + "/" + spotID);
            //Moved to subtree from "~/" to "~/postHistory/spotID/bookingID"
//            reference = reference.child(user.getUid());
            String key = reference.push().getKey();

            reference.addValueEventListener(new ValueEventListener() {
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
            if(!openSlot(postDetailUI.getStartDate().getText().toString(), postDetailUI.getEndDate().getText().toString())){
                //Cancel the booking if the time is not avaliable
                return;
            }

            Snackbar.make(view, "Purchasing the item", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();


            String startDate_i = postDetailUI.getStartDate().getText().toString();
            String endDate_i = postDetailUI.getEndDate().getText().toString();

//            String postId = database.getReference().child("post").child(user.getUid()).push().getKey();
            post = new Post(placeId, spot.getSpotId(), renter.getUid(), title);
//            String bookingID = database.getReference().child("postHistory").child(user.getUid()).push().getKey();



            Renter user_renting = new Renter(user);
            Renting booking = new Renting(spot, user_renting, post.getOwner(), startDate_i, endDate_i);
            booking.setOwner(post.getOwner());
            booking.setRenter(user_renting);

            // ~/ -> ~/postHistory/spotID
            reference = database.getReference().child("postHistory/" + spot.getSpotId());
            booking.setFirebaseKey(reference.push().getKey()); //TODO: might not be the way to get the keyID
           // reference.child("postHistory/" + spotID + "/" + bookingID).setValue(booking);

            //~/postHistory/spotID/bookingID
            reference.child("/" + booking.getFirebaseKey()).setValue(booking);

            Snackbar.make(view.getRootView(), "Success! Booked Spot", Snackbar.LENGTH_LONG);
            //Go back to main
            MainActivity.startIntent(getBaseContext(), user);
        }
    }

    private void getSpot(String placeId){
        reference = database.getReference("spots/" + user.getUid()+"/"+placeId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                     spot = item.getValue(Spot.class);
                     Log.i("spot #", spot.getSpotNumber());
                    Log.i("spot d", spot.getSpotId());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void setUpUI(){
        postDetailUI = new PostDetailActivityViewModel(this);

        postDetailUI.getTitle().setText(title);
        postDetailUI.getAuthor().setText(user.getUserID());
        postDetailUI.getDate().setText(Utilities.getTodayDate());
        if (spot.getPhoto() != null) {
            Glide.with(this).load(spot.getPhoto()).into(postDetailUI.getSpotImage());
        }
        postDetailUI.getDescription().setText(spot.getDescription());
        postDetailUI.getPermit().setText(spot.getPermitRequired());
        postDetailUI.getType().setText(spot.getType());
        postDetailUI.getPrice().setText(String.valueOf(spot.getPrice()));
        postDetailUI.getSpotNum().setText(spot.getSpotNumber());

        if(posting){
            postDetailUI.getPostButton().setVisibility(View.VISIBLE);
            postDetailUI.getBookButton().setVisibility(View.GONE);
            postDetailUI.getCommentsButton().setVisibility(View.GONE);
            postDetailUI.getStartDateButton().setVisibility(View.GONE);
            postDetailUI.getEndDateButton().setVisibility(View.GONE);
            postDetailUI.getStartDate().setVisibility(View.GONE);
            postDetailUI.getEndDate().setVisibility(View.GONE);
            postDetailUI.getDate().setVisibility(View.GONE);

            postDetailUI.getPostButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitPost();
                }
            });

        }else {
            postDetailUI.getPostButton().setVisibility(View.GONE);
            postDetailUI.getBookButton().setVisibility(View.VISIBLE);
            postDetailUI.getCommentsButton().setVisibility(View.VISIBLE);
            postDetailUI.getStartDateButton().setVisibility(View.VISIBLE);
            postDetailUI.getEndDateButton().setVisibility(View.VISIBLE);
            postDetailUI.getStartDate().setVisibility(View.VISIBLE);
            postDetailUI.getEndDate().setVisibility(View.VISIBLE);

            //DATES
            postDetailUI.getStartDateButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postDetailUI.setSelectedDate(postDetailUI.getStartDate());
                    selectDate(view);
                    FLAG_DATE_START = true;
                }
            });

            //DATES
            postDetailUI.getEndDateButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postDetailUI.setSelectedDate(postDetailUI.getEndDate());
                    selectDate(view);
                    FLAG_DATE_END = true;
                }
            });

            //Bookpost
            postDetailUI.getBookButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bookPost(view);
                }
            });

            //View Comments
            postDetailUI.getCommentsButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewComments(view);
                }
            });
        }

    }

    public String selectDate(View view){
        String date = "DATE";

        try{
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        }
        catch(Exception ex){
            Toast.makeText(view.getContext(), "Error setting the DATE", Toast.LENGTH_LONG).show();
        }
        return date;
    }

    //Return false if the given times are not avaliable
    public Boolean openSlot(String startDate, String endDate){
        Date startDate_f;
        Date endDate_f;
        try{
            startDate_f = Utilities.convertStringDate(startDate);
            endDate_f = Utilities.convertStringDate(endDate);
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Error with timestamp format", Toast.LENGTH_SHORT).show();
            return false;
        }

        //startDate is later than endDate
        if(startDate_f.after(endDate_f)){
            Toast.makeText(getApplicationContext(), "'Start Date' cannot be AFTER 'End Date'", Toast.LENGTH_LONG).show();
            return false;
        }

        Date booked_start;
        Date booked_end;

        if(postHistory.getHistory() == null){ return true;}

        for(Renting booking: postHistory.getHistory()){
            try{
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
            }catch(Exception ex){
                Toast.makeText(getApplicationContext(), "Error with DB timestamps", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public static void startIntent(Context context, String title, User user, Spot spot, String placeId, boolean posting ) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.INTENT_EXTRA_SPOT, spot);
        intent.putExtra(Constant.POSTING, true);
        intent.putExtra(Constant.TITLE, title);
        intent.putExtra(Constant.PLACEID, placeId);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, User user, Post post) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.INTENT_EXTRA_POST, post);
        intent.putExtra(Constant.POSTING, false);
        context.startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(year, month,day);
            date = c.getTime();

            postDetailUI.getSelectedDate().setText(dateFormat.format(date));
        }
    }
}
