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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);

        putExtra();
        database = FirebaseDatabase.getInstance();
        //spots.add(new Spot(place.getAddress(), "monthly", "park on driveway on the left", 50, "no", "2", "no", Utilities.getTodayDate()));
        if (mySpotsActivityUIComponents == null) {
            mySpotsActivityUIComponents = new MySpotsViewModel(this);
            mySpotsActivityUIComponents.setUser(user);
        }
        mySpotsActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        mySpotsActivityUIComponents.getActionBar().setTitle("My Spots");
        mySpotsActivityUIComponents.getSpotList().setLayoutManager(mySpotsActivityUIComponents.getLayoutManager());

        getAllSpot(user.getUid());
        adapter = new SpotListAdapter(spots);
        mySpotsActivityUIComponents.getSpotList().setAdapter(adapter);

        mySpotsActivityUIComponents.getAddSpotButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddASpotActivity.startIntent(getApplicationContext(), user, place);
            }
        });
    }

    private void putExtra() {
        place = (Place) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_PLACE);

        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);

    }

    public static void startIntent(Context context, User user, Place place) {
        Log.i("intent to spot ", place.getAddress());
        Log.i("intent to spot ", user.getUid() + " " + user.getEmail());
        Intent intent = new Intent(context, MySpotsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
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
        reference = database.getReference("spots/" + uid);

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
