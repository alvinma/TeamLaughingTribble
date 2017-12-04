package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.thelaughingtribble.parkhere.CreateParkingSpotListing;
import edu.sjsu.thelaughingtribble.parkhere.ParkingPostObject;
import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList.HomePostListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
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
    private User user;
    //post list
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        init();
<<<<<<< HEAD
<<<<<<< HEAD
        getParkingList();
        initList();

        //TODO: moved the order of getPArkingList() from below init() & initList() to TOP
=======
=======
        getParkingList();
        Log.i("post size",""+ posts.size());
        for(Post s: posts){
            Log.i("post", s.getSpotId());
            Log.i("post", s.getOwnerId());
            Log.i("post", s.getTitle());
        }
>>>>>>> fix post db
        if(posts.size()>0){
            initList();
            getParkingList();
        }
>>>>>>> post sumit POST done
        mainActivityUiComponets.getSpotSubmission().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registerParking();
                AddPostActivity.startIntent(getBaseContext(), user);
            }
        });


    }

    private void getOwner(String ownerId){

    }

    private void getSpot(String spotId){

    }
    private void initList() {
        mainActivityUiComponets.getHomePostList().setLayoutManager(mainActivityUiComponets.getLayoutManager());
        mAdapter = new HomePostListAdapter(posts);
        mainActivityUiComponets.getHomePostList().setAdapter(mAdapter);

//        mainActivityUiComponets.getHomePostList().
    }

    private void init() {
        mDatabase = FirebaseDatabase.getInstance();
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);

        menuUIComponents = new NavigationViewModel(this);
        mainActivityUiComponets = new MainActivityViewModel(this);
        menuUIComponents.setUser(user);

        menuUIComponents.setHomeIntent();
        menuUIComponents.setNotificationIntent();
        menuUIComponents.setProfileIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO: research why getParkingList() is commented
        //Current solution calls getParkingList() @onCreate
        //getParkingList();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    private void getParkingList() {
        posts.clear();
        mReference = mDatabase.getReference("post");

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Post post = item.getValue(Post.class);

                    // public Post(String title, Spot spot, Owner owner, String datePosted)
                    Log.i("postonDataChangespot id", post.getSpotId());
                    //Log.i("postonDataChangeowner ", post.getOwnerId());
                    Log.i("postonDataChangetitle ", post.getTitle());
                    posts.add(post);
<<<<<<< HEAD
=======
                    setPosts(posts);
                    //mAdapter.notifyDataSetChanged();
>>>>>>> fix post db
                }
                //Moved the setPosts(posts) outside the loop.
                //logic issue where it will override the post ArrayList if its left in the for loop
                setPosts(posts);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                Toast.makeText(getApplicationContext(), "ERROR: DatabaseError !!!!!", Toast.LENGTH_SHORT).show();
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

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
