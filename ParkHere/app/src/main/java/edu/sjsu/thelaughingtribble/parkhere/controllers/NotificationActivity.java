package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NotificationActivityViewModel;

public class NotificationActivity extends AppCompatActivity {
    private NotificationActivityViewModel notificationActivityUIComponents;
    private NavigationViewModel menuUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationActivityUIComponents = new NotificationActivityViewModel(this);
        notificationActivityUIComponents.setToast("Not available");
        notificationActivityUIComponents.getActionBar().setTitle("Notification");
        menuUIComponents = new NavigationViewModel(this);
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
