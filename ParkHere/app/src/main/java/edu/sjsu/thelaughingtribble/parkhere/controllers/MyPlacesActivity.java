package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.adapters.placeList.PlaceListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyPlacesActivityViewModel;

public class MyPlacesActivity extends BaseActivity {
    MyPlacesActivityViewModel myPlacesActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Place> places = new ArrayList<>();
    private User user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private boolean posting = false;
    private String title = "";

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MyPlacesActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, User user, String title, boolean posting) {
        Intent intent = new Intent(context, MyPlacesActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.POSTING, posting);
        intent.putExtra(Constant.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        database = FirebaseDatabase.getInstance();
        getDataFromIntent();
        setupUI();


        getAllPlaces(user.getUid());


        myPlacesActivityUIComponents.getPlaceList().setLayoutManager(myPlacesActivityUIComponents.getLayoutManager());

        if (posting) {
            adapter = new PlaceListAdapter(places, user, title, posting);
        } else {
            adapter = new PlaceListAdapter(places, user);
        }
        myPlacesActivityUIComponents.getPlaceList().setAdapter(adapter);


    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public void setupUI() {
        myPlacesActivityUIComponents = new MyPlacesActivityViewModel(this);
        myPlacesActivityUIComponents.setUser(user);
        if (posting) {
            if (myPlacesActivityUIComponents.getActionBar() != null) {
                myPlacesActivityUIComponents.getActionBar().setTitle("Select a place");
                myPlacesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } else {
            if (myPlacesActivityUIComponents.getActionBar() != null) {
                myPlacesActivityUIComponents.getActionBar().setTitle("My Places");
                myPlacesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void getDataFromIntent() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        if (getIntent().hasExtra(Constant.POSTING)) {
            posting = getIntent().getExtras().getBoolean(Constant.POSTING);
        } else {
            posting = false;
        }

        if (getIntent().hasExtra(Constant.TITLE)) {
            title = getIntent().getExtras().getString(Constant.TITLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ProfileActivity.startIntent(this, user);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllPlaces(String uid) {
        places.clear();
        reference = database.getReference("places/" + uid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Place place = item.getValue(Place.class);
                    Log.i("add", place.getAddress());
                    Log.i("key", place.getFirebaseKey());
                    places.add(place);
                    setPlaces(places);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
