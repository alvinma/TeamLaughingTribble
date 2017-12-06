package edu.sjsu.thelaughingtribble.parkhere;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.CommentAndRating;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;


/**
 * Created by Alvin on 11/20/2017.
 */

public class RateAndComment extends AppCompatActivity{

    RatingBar rating;
    TextView currentTime;
    EditText comment;
    EditText renterName;
    Button submitFeedback;
    double grade;
    int nextCommentID = 0;
    int currCommentID = 0;
    long date = System.currentTimeMillis();
    String spotID = "";

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_and_comment);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            currCommentID = extras.getInt("CommentCount");
            spotID = extras.getString("SpotID");
        }
        nextCommentID = 1 + currCommentID;

        rating = (RatingBar) findViewById(R.id.rateSpot);
        currentTime = (TextView) findViewById(R.id.timeStamp);
        comment = (EditText) findViewById(R.id.comment);
        renterName = (EditText) findViewById(R.id.renterName);
        submitFeedback = (Button) findViewById(R.id.submitFeedback);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy - h:mm a");
        String dateString = dateFormat.format(date);
        currentTime.setText(dateString);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                grade = rating;
            }
        });

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // put code to process data here

                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy - h:mm a");
                String dateString = dateFormat.format(date);
                currentTime.setText(dateString);

                //if (grade != null && renterName != null && comment != null && commentID != 0 && currentTime != null) {
                CommentAndRating commentAndRating = null;
                commentAndRating = new CommentAndRating(grade, renterName.getText().toString(), comment.getText().toString(), currentTime.getText().toString(), nextCommentID);
                database.child("rating_comment/" + spotID + "/" + nextCommentID).setValue(commentAndRating);
                //}
                finish();
            }
        });
    }
}
