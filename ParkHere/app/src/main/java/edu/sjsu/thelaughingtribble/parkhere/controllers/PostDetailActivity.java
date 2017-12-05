package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
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
    private PostDetailActivityViewModel postDetailUI;
    private String placeId;
    private FirebaseDatabase database;
    private DatabaseReference reference;
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
    //Edited user ->renter
    private void bookPost(){
        String postId = database.getReference().child("post").child(user.getUid()).push().getKey();
        post = new Post(placeId, spot.getSpotId(), renter.getUid(), title);
        Renting booking = new Renting();
        booking.setOwner(post.getOwner());
        booking.setRenter(renter);
        booking.setStartDate("START_DATE");
        booking.setEndDate("END_DATE");
        database.getReference().child("postHistory/" + spot.getFirebaseKey() + "/" + postId).setValue(booking);


        //Go back to main
        MainActivity.startIntent(getBaseContext(), user);
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

            postDetailUI.getBookButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bookPost();
                }
            });

            postDetailUI.getCommentsButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Uses Alvins part
//                    Intent intent = new Intent(view.getContext(), COMMENTS_CLASS.class);
//                    intent.putExtra(Constant.INTENT_EXTRA_POST, post);
//                    view.getContext().startActivity(intent);

                    Toast.makeText(view.getContext(), "Loading Comments...", Toast.LENGTH_SHORT).show();
                }
            });
        }

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
}
