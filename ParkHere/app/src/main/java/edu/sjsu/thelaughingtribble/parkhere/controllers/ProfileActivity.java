package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.ProfileActivityViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ProfileActivityViewModel profileActivityUIComponents;
    private NavigationViewModel menuUIComponents;
    private User user;

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        if (profileActivityUIComponents.getActionBar() != null) {
            profileActivityUIComponents.getActionBar().setTitle("Profile");
            profileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(false);
        }

        profileActivityUIComponents.getLogoutLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.signOut(FirebaseAuth.getInstance());
                Intent intent = new Intent(profileActivityUIComponents.getContext(),
                        LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        profileActivityUIComponents = new ProfileActivityViewModel(this);
        menuUIComponents = new NavigationViewModel(this);

        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);

        profileActivityUIComponents.setUser(user);
        menuUIComponents.setUser(user);
        Log.i("Profile Activity", menuUIComponents.getUser().getUid() + " " + menuUIComponents.getUser().getEmail());
        menuUIComponents.setHomeIntent();
        menuUIComponents.setNotificationIntent();
        menuUIComponents.setProfileIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
