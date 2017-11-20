package edu.sjsu.thelaughingtribble.parkhere;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

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

    long date = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_and_comment);

        rating = (RatingBar) findViewById(R.id.rateSpot);
        currentTime = (TextView) findViewById(R.id.timeStamp);
        comment = (EditText) findViewById(R.id.comment);
        renterName = (EditText) findViewById(R.id.renterName);
        submitFeedback = (Button) findViewById(R.id.submitFeedback);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy - h:mm a");
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

                finish();
            }
        });
    }
}
