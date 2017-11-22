package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;

import static android.R.attr.data;

/**
 * Created by jennifernghinguyen on 11/2/17.
 * view models for common component:
 * action bar
 * context
 * toast
 */

public class ActivityCommonViewModel {
    private android.support.v7.app.ActionBar actionBar; //action bar for activity
    private Context context;
    private LinearLayoutManager layoutManager;

    //for intents
    private User user;
    private Spot spot;
    private Place place;
    private Vehicle vehicle;

    public ActivityCommonViewModel(Context context) {
        this.context = context;
        actionBar = ((AppCompatActivity) context).getSupportActionBar();
        this.layoutManager = new LinearLayoutManager(context);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public android.support.v7.app.ActionBar getActionBar() {
        return actionBar;
    }

    public void setActionBar(android.support.v7.app.ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /*
    show short TOAST
     */

    public void setToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
