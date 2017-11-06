package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.adapters.spotList.SpotListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList.VehicleListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyRentingSpotsActivityViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MySpotsViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyVehiclesActivityViewModel;

import static edu.sjsu.thelaughingtribble.parkhere.Utils.Constant.INTENT_EXTRA_PLACE;

public class MySpotsActivity extends AppCompatActivity {
    private MySpotsViewModel mySpotsActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Spot> spots = new ArrayList<>();
    Place place = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);

        place = (Place) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_PLACE);

        spots.add(new Spot(place.getAddress(), "monthly", "park on driveway on the left", 50, "no", "2", "no", Utilities.getTodayDate()));
        if(mySpotsActivityUIComponents==null){
            mySpotsActivityUIComponents = new MySpotsViewModel(this);
        }
        mySpotsActivityUIComponents.getActionBar().setTitle("My Spots");
        mySpotsActivityUIComponents.getSpotList().setLayoutManager(mySpotsActivityUIComponents.getLayoutManager());

        adapter = new SpotListAdapter(spots);
        mySpotsActivityUIComponents.getSpotList().setAdapter(adapter);
    }

    public static void startIntent(Context context, Place place){
        Log.i("intent to spot ", place.getAddress());
        Intent intent = new Intent(context, MySpotsActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        context.startActivity(intent);
    }
}
