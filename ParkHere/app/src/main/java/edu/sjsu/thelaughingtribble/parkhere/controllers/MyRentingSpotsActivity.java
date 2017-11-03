package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyRentingSpotsActivityViewModel;

public class MyRentingSpotsActivity extends AppCompatActivity {
    MyRentingSpotsActivityViewModel myRentingSpotsActivityUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_renting_spots);
        myRentingSpotsActivityUIComponents = new MyRentingSpotsActivityViewModel(this);
        if(myRentingSpotsActivityUIComponents.getActionBar()!=null){
            myRentingSpotsActivityUIComponents.getActionBar().setTitle("My Renting Spot");
            myRentingSpotsActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
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
