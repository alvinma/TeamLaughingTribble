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

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.adapters.spotList.SpotListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MySpotsViewModel;

public class MySpotsActivity extends AppCompatActivity {
    private MySpotsViewModel mySpotsActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Spot> spots = new ArrayList<>();
    Place place = null;
    User user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private boolean posting = false;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);

        putExtra();
        database = FirebaseDatabase.getInstance();
        //spots.add(new Spot(place.getAddress(), "monthly", "park on driveway on the left", 50, "no", "2", "no", Utilities.getTodayDate()));
       setUpUI();

        mySpotsActivityUIComponents.getSpotList().setLayoutManager(mySpotsActivityUIComponents.getLayoutManager());

        getAllSpot(user.getUid());
        if(posting) {
            adapter = new SpotListAdapter(spots, user, title, place.getFirebaseKey(), posting);
        }else {
            adapter = new SpotListAdapter(spots, user);
        }
        mySpotsActivityUIComponents.getSpotList().setAdapter(adapter);

        mySpotsActivityUIComponents.getAddSpotButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddASpotActivity.startIntent(getApplicationContext(), user, place);
            }
        });
<<<<<<< HEAD
=======

        Log.i("title", title);
        Log.i("placeid", place.getFirebaseKey());
        Log.i("posting", " "+posting);
>>>>>>> parent of 0cfc810... Merge pull request #19 from alvinma/users/jennifer_n/main_branch
    }

    private void setUpUI(){
        if (mySpotsActivityUIComponents == null) {
            mySpotsActivityUIComponents = new MySpotsViewModel(this);
            mySpotsActivityUIComponents.setUser(user);
        }
        if(posting){
            mySpotsActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
            mySpotsActivityUIComponents.getActionBar().setTitle("Choose a spot");

        }else {
            mySpotsActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
            mySpotsActivityUIComponents.getActionBar().setTitle("My Spots");
        }
    }

    private void putExtra() {
        place = (Place) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_PLACE);
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);

        if(getIntent().hasExtra(Constant.POSTING)){
            posting = getIntent().getExtras().getBoolean(Constant.POSTING);
        }

        if(getIntent().hasExtra(Constant.TITLE)){
            title = getIntent().getExtras().getString(Constant.TITLE);
        }
    }

<<<<<<< HEAD
=======
    public static void startIntent(Context context, User user, Place place) {
        Intent intent = new Intent(context, MySpotsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

>>>>>>> parent of 0cfc810... Merge pull request #19 from alvinma/users/jennifer_n/main_branch
    public static void startIntent(Context context, User user, Place place, String title, boolean posting) {
        Intent intent = new Intent(context, MySpotsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.TITLE, title);
        intent.putExtra(Constant.POSTING, posting);
        context.startActivity(intent);
    }
    public ArrayList<Spot> getSpots() {
        return spots;
    }

    public void setSpots(ArrayList<Spot> spots) {
        this.spots = spots;
    }

    private void getAllSpot(String uid) {
        spots.clear();
        reference = database.getReference("spots/"+uid+"/" + place.getFirebaseKey());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Spot spot = item.getValue(Spot.class);
                    spots.add(spot);
                    setSpots(spots);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
