package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.thelaughingtribble.parkhere.CreateParkingSpotListing;
import edu.sjsu.thelaughingtribble.parkhere.ParkingPostObject;
import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.GoogleMaps;
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


    //post list
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        posts.add(new Post("montly parking near SJSU 1", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 2", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 3", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 4", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 5", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 6", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));
        posts.add(new Post("montly parking near SJSU 7", new Spot("100 senter rd", "montly", "park in the driveway", 50, false, "1", "no", "12-1-2017"), new Owner("1", "John", "Doe", "john@gmail.com"), Utilities.getTodayDate()));

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
    public void searchMapView(){
        Intent intent = new Intent(this, GoogleMaps.class);
        this.startActivity(intent);
    }
}