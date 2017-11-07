package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.ProfileActivityViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ProfileActivityViewModel profileActivityUIComponents;
    private NavigationViewModel menuUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileActivityUIComponents = new ProfileActivityViewModel(this);
        menuUIComponents = new NavigationViewModel(this);

        if(profileActivityUIComponents.getActionBar()!=null){
            profileActivityUIComponents.getActionBar().setTitle("Profile");
            profileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        profileActivityUIComponents.getLogoutLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.signOut(FirebaseAuth.getInstance());
                Intent intent = new Intent(profileActivityUIComponents.getContext(), LoginActivity.class);
                startActivity(intent);
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
