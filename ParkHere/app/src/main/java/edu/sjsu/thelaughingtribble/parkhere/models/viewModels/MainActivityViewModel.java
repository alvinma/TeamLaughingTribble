package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 10/30/17.
 */
/*
layout: activity_main.xml
 */
public class MainActivityViewModel {

  private FloatingActionButton spotSubmission;

  public MainActivityViewModel(Context context){
    this.spotSubmission = (FloatingActionButton) ((Activity) context).findViewById(R.id.Spot_Submission);;
  }

  public FloatingActionButton getSpotSubmission(){
    return this.spotSubmission;
  }



}
