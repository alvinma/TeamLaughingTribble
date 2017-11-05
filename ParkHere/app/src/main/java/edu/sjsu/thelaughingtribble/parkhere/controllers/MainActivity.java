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

    private DatabaseReference mDatabase;
    private FirebaseDatabase mReference;

    private static final String TAG = "MAINACTIVITY|___|";

    //post list
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        getParkingList();

        posts.add(new Post("montly parking near SJSU 1", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 2", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 3", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 4", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 5", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 6", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
//        posts.add(new Post("montly parking near SJSU 7", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));

        menuUIComponents = new NavigationViewModel(this);
        mainActivityUiComponets = new MainActivityViewModel(this);

        mainActivityUiComponets.getSpotSubmission().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerParking();
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        mainActivityUiComponets.getHomePostList().setLayoutManager(mLayoutManager);

        mAdapter = new HomePostListAdapter(posts);
        mainActivityUiComponets.getHomePostList().setAdapter(mAdapter);


    }

    private void getParkingList(){

        //Query query = mDatabase.getRef().orderByChild('users').limitToFirst(5);

// Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("post");
        System.out.println("DB REFERENCE #### #### ### : " + ref.toString());
 

// Attach a listener to read the data at our posts reference
//        ref.addValueEventListener(new ValueEventListener() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            try {
                System.out.println("------- ------ ---- GETTING POST from DB");
                System.out.println("DB info: vvvvvv");
                System.out.println("child_count: " + dataSnapshot.getChildrenCount());
                //System.out.println("snapshot.toString: " + dataSnapshot.toString());
                //System.out.println("snapshot.toString: " + dataSnapshot.getChildren());

                System.out.println("Looping through children vvvvv");
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Post post = item.getValue(Post.class);
//                    System.out.println("Item: " + item.toString());
//                    System.out.println("POST: " + post.toString());
//                    System.out.println("POST_title: " + post.getTitle());
//
//                    //Owner owner_post = post.getValue(Post.class);
//
//                    Owner owner = post.getOwner();
//                    System.out.println("Owner ID: " + owner.getUserID());
//                    System.out.println("Full Name: " + owner.getFullName());
//                    System.out.println("email: " + owner.getEmail());
//
//                    Spot spot = post.getSpot();
//                    System.out.println("Spot: " + spot.toString());
//                    System.out.println("Spot_Address: " + spot.getAddress());
//
//
//                    System.out.println("TotalGrade: " + String.valueOf(post.getTotalGrade()));
//                    System.out.println("DatePosted: " + post.getDatePosted());
//                    System.out.println("------- ------ ---- END of post ^^^^^");
                    posts.add(post);
                }
                System.out.println("DONE loping children ^^^^");
            }catch(DatabaseException db_ex){
                System.out.println("ERROR :::::: Database Exception: " + db_ex.getMessage());
                System.out.println("ERROR :::::: Database Exception: " + db_ex.toString());
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(" ********** ****** ***** *** **** FAIL reading DB");
                System.out.println("The read failed: " + databaseError.getCode());
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