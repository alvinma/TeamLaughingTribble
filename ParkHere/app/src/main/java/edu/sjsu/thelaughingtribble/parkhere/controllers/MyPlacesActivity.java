package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyPlacesActivityViewModel;

public class MyPlacesActivity extends AppCompatActivity {
    MyPlacesActivityViewModel myPlacesActivityUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        myPlacesActivityUIComponents = new MyPlacesActivityViewModel(this);
        if(myPlacesActivityUIComponents.getActionBar()!=null){
            myPlacesActivityUIComponents.getActionBar().setTitle("My Places");
            myPlacesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
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
