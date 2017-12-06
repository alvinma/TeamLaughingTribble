package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import edu.sjsu.thelaughingtribble.parkhere.CommentView;
import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MainActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.NotificationActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.ProfileActivity;

/**
 * Created by jennifernghinguyen on 10/30/17.
 */
/*
layout: activity_navigation.xml
 */
public class NavigationViewModel extends ActivityCommonViewModel  {


    private LinearLayout homeLayout;
    private LinearLayout notificationLayout;
    private LinearLayout profileLayout;

    public NavigationViewModel(Context context) {
        super(context);

        this.homeLayout = (LinearLayout) ((Activity) context).findViewById(R.id.home_layout);
        this.notificationLayout = (LinearLayout) ((Activity) context).findViewById(R.id.notification_layout);
        this.profileLayout = (LinearLayout) ((Activity) context).findViewById(R.id.profile_layout);

    }

    public LinearLayout getHomeLayout() {
        return this.homeLayout;
    }

    public LinearLayout getNotificationLayout() {
        return this.notificationLayout;
    }

    public LinearLayout getNavigationLayout() {
        return this.profileLayout;
    }

    public void setHomeIntent() {
        this.homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getUser()!=null) {
                    MainActivity.startIntent(getContext(), getUser());
                }else {
                    Log.i("setHomeIntent", "user null");
                }
            }
        });

    }

    public void setNotificationIntent() {
        this.notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(), NotificationActivity.class);
                getContext().startActivity(intent);*/
                if(getUser()!=null) {
                    //NotificationActivity.startIntent(getContext(), getUser());    // alvin change
                    CommentView.startIntent(getContext(), getUser());
                }else {
                    Log.i("setNotificationIntent", "user null");
                }

            }
        });
    }

    public void setProfileIntent() {
        this.profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getUser()!=null) {
                    ProfileActivity.startIntent(getContext(), getUser());
                }else {
                    Log.i("setProfileIntent", "user null");
                }
            }
        });
    }

}
