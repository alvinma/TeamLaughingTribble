package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    public ActivityCommonViewModel(Context context) {
        this.context = context;
        actionBar = ((AppCompatActivity) context).getSupportActionBar();
    }

    public android.support.v7.app.ActionBar getActionBar() {
        return actionBar;
    }

    public void setActionBar(android.support.v7.app.ActionBar actionBar) {
        this.actionBar = actionBar;
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