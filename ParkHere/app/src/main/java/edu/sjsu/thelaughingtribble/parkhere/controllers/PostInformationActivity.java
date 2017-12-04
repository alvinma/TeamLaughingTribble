package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList.homePostListItemViewHolder;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

public class PostInformationActivity extends AppCompatActivity {

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

    //TODO: idea 1, add post info from the previous posting.
    //just add button to book post

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_information);
//        setContentView(R.layout.content_post_information);

        mDatabase = FirebaseDatabase.getInstance();
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Post Information");
        setSupportActionBar(toolbar);

        init();
        extractBundle(savedInstanceState);
        updateUI();
    }

    public void init(){
        postImage = (ImageView) findViewById(R.id.post_image_info);
        postTitle = (TextView) findViewById(R.id.post_location_info);
        postPrice = (TextView) findViewById(R.id.post_price_info);
        postOwner = (TextView) findViewById(R.id.post_owner_name);
//        datePosted = (TextView) findViewById(R.id.post_date_posted);
        postDescription = (TextView) findViewById(R.id.post_description_info);

        purchaseButton = (Button) findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Purchasing the item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                //TODO: uncomment
//                Intent intent_purchase = new Intent(view, BookPost.class);
//                view.getContext().startActivity(intent_purchase);
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
    }

    public boolean isBooked(){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        Toast.makeText(getApplicationContext(), "TIME: " + currentDateTimeString, Toast.LENGTH_LONG).show();
// textView is the TextView view that should display it
//        textView.setText(currentDateTimeString);
//
//        String dateTimeAsString = new DateTime( secondsSinceUnixEpoch * 1000, DateTimeZone.UTC ).toString();

        return true;
    }

    public void onClickPurchase(){

    }


    public Boolean isBookedDB() {
        //TODO: from DB get the list of booked times
        return true;
    }
}
