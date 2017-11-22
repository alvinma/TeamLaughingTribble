package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NotificationActivityViewModel;

public class NotificationActivity extends AppCompatActivity {
    private NotificationActivityViewModel notificationActivityUIComponents;
    private NavigationViewModel menuUIComponents;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();
        notificationActivityUIComponents.setToast("Not available");
        notificationActivityUIComponents.getActionBar().setTitle("Notification");

    }

    private void init() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        Log.i("Notification", user.getUid() + " " + user.getEmail());
        notificationActivityUIComponents = new NotificationActivityViewModel(this);
        menuUIComponents = new NavigationViewModel(this);
        menuUIComponents.setUser(user);
        menuUIComponents.setHomeIntent();
        menuUIComponents.setNotificationIntent();
        menuUIComponents.setProfileIntent();
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

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
