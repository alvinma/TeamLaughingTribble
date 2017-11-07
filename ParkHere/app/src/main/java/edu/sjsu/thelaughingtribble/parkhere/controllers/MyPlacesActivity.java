package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.adapters.placeList.PlaceListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList.VehicleListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyPlacesActivityViewModel;

public class MyPlacesActivity extends AppCompatActivity {
    MyPlacesActivityViewModel myPlacesActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Place> places = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        places.add(new Place("1 Washington Sq, San Jose, CA 95192"));
        myPlacesActivityUIComponents = new MyPlacesActivityViewModel(this);
        if(myPlacesActivityUIComponents.getActionBar()!=null){
            myPlacesActivityUIComponents.getActionBar().setTitle("My Places");
            myPlacesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        myPlacesActivityUIComponents.getPlaceList().setLayoutManager(myPlacesActivityUIComponents.getLayoutManager());

        adapter = new PlaceListAdapter(places);
        myPlacesActivityUIComponents.getPlaceList().setAdapter(adapter);

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
