package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyProfileActivityViewModel;

public class MyProfileActivity extends AppCompatActivity {

    MyProfileActivityViewModel myProfileActivityUIComponents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        myProfileActivityUIComponents = new MyProfileActivityViewModel(this);
        if(myProfileActivityUIComponents.getActionBar()!=null){
            myProfileActivityUIComponents.getActionBar().setTitle("My Profile");
            myProfileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
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
