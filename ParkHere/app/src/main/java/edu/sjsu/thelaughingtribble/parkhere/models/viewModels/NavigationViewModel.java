package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 10/30/17.
 */
/*
layout: activity_navigation.xml
 */
public class NavigationViewModel {

  private LinearLayout homeLayout;
  private LinearLayout notificationLayout;
  private LinearLayout profileLayout;

  public NavigationViewModel(Context context){
    this.homeLayout = (LinearLayout) ((Activity) context).findViewById(R.id.home_layout);
    this.notificationLayout = (LinearLayout) ((Activity) context).findViewById(R.id.notification_layout);
    this.profileLayout = (LinearLayout) ((Activity) context).findViewById(R.id.profile_layout);
  }

  public LinearLayout getHomeLayout(){
    return this.homeLayout;
  }

  public LinearLayout getNotificationLayout(){
    return this.notificationLayout;
  }

  public LinearLayout getNavigationLayout(){
    return this.profileLayout;
  }

}
