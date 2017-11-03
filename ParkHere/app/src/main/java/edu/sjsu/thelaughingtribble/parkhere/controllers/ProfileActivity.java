package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.ProfileActivityViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ProfileActivityViewModel profileActivityUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileActivityUIComponents = new ProfileActivityViewModel(this);

        if(profileActivityUIComponents.getActionBar()!=null){
            profileActivityUIComponents.getActionBar().setTitle("Profile");
            profileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
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
