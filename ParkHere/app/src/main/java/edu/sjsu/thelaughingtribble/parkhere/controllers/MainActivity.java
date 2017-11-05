package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.thelaughingtribble.parkhere.CreateParkingSpotListing;
import edu.sjsu.thelaughingtribble.parkhere.ParkingPostObject;
import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList.HomePostListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Owner;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MainActivityViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> tempDB = new ArrayList<Integer>();
    public static List<ParkingPostObject> tempObjectDB = new ArrayList<ParkingPostObject>();
    private NavigationViewModel menuUIComponents;
    private MainActivityViewModel mainActivityUiComponets;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private static final String TAG = "MAINACTIVITY|___|";

    //post list
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance();


        posts.add(new Post("montly parking near SJSU 1", new Spot("100 senter rd", "montly", "park in the driveway", 50, "false", "1", "12-1-2017", "http://parkeasier.com/wp-content/uploads/2014/12/Homepage_Find_a_Spot_section2.jpg"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));



        menuUIComponents = new NavigationViewModel(this);
        mainActivityUiComponets = new MainActivityViewModel(this);

        mainActivityUiComponets.getSpotSubmission().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerParking();
            }
        });

        mainActivityUiComponets.getHomePostList().setLayoutManager(mainActivityUiComponets.getLayoutManager());
        mAdapter = new HomePostListAdapter(posts);
        mainActivityUiComponets.getHomePostList().setAdapter(mAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        getParkingList();
    }

    private void getParkingList(){
        mReference = mDatabase.getReference("post");

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Post post = item.getValue(Post.class);
                    posts.add(post);
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
}