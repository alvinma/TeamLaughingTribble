package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;

/**
 * Created by jennifernghinguyen on 10/30/17.
 */
/*
layout: activity_main.xml
 */
public class MainActivityViewModel extends ActivityCommonViewModel {

  private FloatingActionButton spotSubmission;
  private RecyclerView homePostList;



  public MainActivityViewModel(Context context){
    super(context);
    this.spotSubmission = (FloatingActionButton) ((Activity) context).findViewById(R.id.Spot_Submission);
    this.homePostList = (RecyclerView) ((Activity) context).findViewById(R.id.home_post_list);
  }

  public FloatingActionButton getSpotSubmission(){
    return this.spotSubmission;
  }

  public void setSpotSubmission(FloatingActionButton spotSubmission) {
    this.spotSubmission = spotSubmission;
  }

  public RecyclerView getHomePostList() {
    return homePostList;
  }

  public void setHomePostList(RecyclerView homePostList) {
    this.homePostList = homePostList;
  }
}
