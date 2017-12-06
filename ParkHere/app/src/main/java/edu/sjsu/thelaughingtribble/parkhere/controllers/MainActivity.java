package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Owner;
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
        initList();
        getParkingList();


        mainActivityUiComponets.getSpotSubmission().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registerParking();
                AddPostActivity.startIntent(getBaseContext(), user);
            }
        });

    }

    private void getOwner(final String title, final String ownerId, final String placeId, final String spotId, final String datePosted, final double totalGrade) {
        mReference = FirebaseDatabase.getInstance().getReference("users/" + ownerId);

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("owner snapshot", dataSnapshot.toString());
                Log.i("email", dataSnapshot.getValue(Owner.class).getUserID());
                Owner owner = (Owner) dataSnapshot.getValue(Owner.class);

                getSpot(title, spotId, owner, placeId, datePosted, totalGrade);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    private void getSpot(final String title, String spotId, final Owner owner, String placeId, final String datePosted, final double totalGrade) {
        mReference = FirebaseDatabase.getInstance().getReference("spots/" + owner.getUid() +"/" + placeId+"/"+spotId);

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("getSpot snapshot", dataSnapshot.toString());
                Spot spot =  dataSnapshot.getValue(Spot.class);
                Post post = new Post(title, spot, owner, datePosted, totalGrade);



                posts.add(post);
                setPosts(posts);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    private void initList() {
        mainActivityUiComponets.getHomePostList().setLayoutManager(mainActivityUiComponets.getLayoutManager());
        mAdapter = new HomePostListAdapter(posts, user);
        mainActivityUiComponets.getHomePostList().setAdapter(mAdapter);

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
        //getParkingList();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }


    public void getParkingList() {
        posts.clear();

        mReference = FirebaseDatabase.getInstance().getReference("post");

        // Attach a listener to read the data at our posts reference
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Post post = item.getValue(Post.class);

                    getOwner(post.getTitle(), post.getAuthorId(), post.getPlaceID(),post.getSpotId(), post.getDatePosted(), post.getTotalGrade());
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

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
