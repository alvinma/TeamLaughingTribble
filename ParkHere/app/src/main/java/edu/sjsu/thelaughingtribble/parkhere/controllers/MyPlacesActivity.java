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

public class MyPlacesActivity extends AppCompatActivity {
    MyPlacesActivityViewModel myPlacesActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Place> places = new ArrayList<>();
    private User user;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        database = FirebaseDatabase.getInstance();
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        myPlacesActivityUIComponents = new MyPlacesActivityViewModel(this);
        myPlacesActivityUIComponents.setUser(user);
        if (myPlacesActivityUIComponents.getActionBar() != null) {
            myPlacesActivityUIComponents.getActionBar().setTitle("My Places");
            myPlacesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getAllPlaces(user.getUid());


        myPlacesActivityUIComponents.getPlaceList().setLayoutManager(myPlacesActivityUIComponents.getLayoutManager());

        adapter = new PlaceListAdapter(places, user);
        myPlacesActivityUIComponents.getPlaceList().setAdapter(adapter);

    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
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

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MyPlacesActivity.class);
        Log.i("my places startIntent", user.getUid() + " " + user.getEmail());
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

}
