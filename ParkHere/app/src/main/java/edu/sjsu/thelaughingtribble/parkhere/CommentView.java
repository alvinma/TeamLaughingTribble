package edu.sjsu.thelaughingtribble.parkhere;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.CommentAndRating;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;

public class CommentView extends AppCompatActivity {

    private NavigationViewModel menuUIComponents;
    private CommentViewModel mainActivityUiComponets;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private static final String TAG = "COMMENTVIEW|___|";
    private User user;
    private Spot spot;
    //list
    ArrayList<CommentAndRating> commentAndRatings = new ArrayList<>();

    RatingBar rateview;
    TextView rateCount;

    int counter = 0;
    String tempSpotID = "101508950611960214124_spot1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spot = (Spot) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_SPOT);
        tempSpotID = spot.getSpotId();

        getSupportActionBar().hide();
        setContentView(R.layout.comment_view);
        rateview = (RatingBar) findViewById(R.id.spotRating);
        rateCount = (TextView) findViewById(R.id.rateCount);

        init();
        initList();
        getParkingList();
    }

    private void initList() {
        mainActivityUiComponets.getCommentList().setLayoutManager(mainActivityUiComponets.getLayoutManager());
        mAdapter = new CommentViewAdapter(commentAndRatings);
        mainActivityUiComponets.getCommentList().setAdapter(mAdapter);

    }

    private void init() {
        mDatabase = FirebaseDatabase.getInstance();
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        Log.i("Main", user.getUid() + " " + user.getEmail());
        menuUIComponents = new NavigationViewModel(this);
        mainActivityUiComponets = new CommentViewModel(this);
        menuUIComponents.setUser(user);
        Log.i("Main menuUIComponents", menuUIComponents.getUser().getUid() + " " + menuUIComponents.getUser().getEmail());
        menuUIComponents.setHomeIntent();
        menuUIComponents.setNotificationIntent();
        menuUIComponents.setProfileIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getParkingList();
    }

    public ArrayList<CommentAndRating> getCommentAndRating() {
        return commentAndRatings;
    }

    public void setCommentAndRatings(ArrayList<CommentAndRating> commentAndRatings) {
        this.commentAndRatings = commentAndRatings;
    }

    private void getParkingList() {
        commentAndRatings.clear();
        mReference = mDatabase.getReference("rating_comment/" + tempSpotID);

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentAndRatings.clear();
                double rating = 0.0;
                counter = 0;
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    CommentAndRating commentAndRating = item.getValue(CommentAndRating.class);
                    commentAndRatings.add(commentAndRating);
                    setCommentAndRatings(commentAndRatings);
                    rating += commentAndRating.getGrade();
                    counter++;
                    rateview.setRating((float)(rating/counter));
                    rateCount.setText("(Raters: " + counter + ")");
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    /*public void parkingList(View v){
        Intent intent = new Intent(this, ParkingSpotList.class);
        this.startActivity(intent);
    }*/

    public void registerParking() {
        Intent intent = new Intent(this, CreateParkingSpotListing.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // no usage
    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, CommentView.class);
        Log.i("Main Intent", user.getUid() + " " + user.getEmail());
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    public void addComment(View view){
        Intent intent = new Intent(this, RateAndComment.class);
        intent.putExtra("CommentCount", counter);
        intent.putExtra("SpotID", tempSpotID);
        startActivity(intent);
    }
}
