package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyVehiclesActivityViewModel;

public class MyVehiclesActivity extends AppCompatActivity {
    MyVehiclesActivityViewModel myVehiclesActivityUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicles);
        myVehiclesActivityUIComponents = new MyVehiclesActivityViewModel(this);
        if(myVehiclesActivityUIComponents.getActionBar()!=null){
            myVehiclesActivityUIComponents.getActionBar().setTitle("My Vehicles");
            myVehiclesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
