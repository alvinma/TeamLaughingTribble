package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyPlacesActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyProfileActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyRentingSpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyVehiclesActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.ProfileActivity;

/**
 * Created by jennifernghinguyen on 11/2/17.
 */

public class ProfileActivityViewModel extends ActivityCommonViewModel {

    private LinearLayout myProfileLayout;
    private LinearLayout myVehicleLayout;
    private LinearLayout myPlacesLayout;
    private LinearLayout myRentingSpotsLayout;
    private LinearLayout logoutLayout;

    public ProfileActivityViewModel(Context context) {
        super(context);
        this.myProfileLayout = (LinearLayout)  ((Activity) context).findViewById(R.id.myprofile_layout);
        this.myVehicleLayout = (LinearLayout)  ((Activity) context).findViewById(R.id.myvehicle_layout);
        this.myPlacesLayout = (LinearLayout)  ((Activity) context).findViewById(R.id.myplaces_layout);
        this.myRentingSpotsLayout = (LinearLayout)  ((Activity) context).findViewById(R.id.myrentingspots_layout);
        this.logoutLayout = (LinearLayout)  ((Activity) context).findViewById(R.id.logout_layout);
        setMyVehiclesIntent();
        setMyPlacesIntent();
        setMyprofileIntent();
        setMyRentingSpotsIntent();
    }

    public LinearLayout getMyProfileLayout() {
        return myProfileLayout;
    }

    public void setMyProfileLayout(LinearLayout myProfileLayout) {
        this.myProfileLayout = myProfileLayout;
    }

    public LinearLayout getMyVehicleLayout() {
        return myVehicleLayout;
    }

    public void setMyVehicleLayout(LinearLayout myVehicleLayout) {
        this.myVehicleLayout = myVehicleLayout;
    }

    public LinearLayout getMyPlacesLayout() {
        return myPlacesLayout;
    }

    public void setMyPlacesLayout(LinearLayout myPlacesLayout) {
        this.myPlacesLayout = myPlacesLayout;
    }

    public LinearLayout getMyRentingSpotsLayout() {
        return myRentingSpotsLayout;
    }

    public void setMyRentingSpotsLayout(LinearLayout myRentingSpotsLayout) {
        this.myRentingSpotsLayout = myRentingSpotsLayout;
    }

    public LinearLayout getLogoutLayout() {
        return logoutLayout;
    }

    public void setLogoutLayout(LinearLayout logoutLayout) {
        this.logoutLayout = logoutLayout;
    }

    private void setMyprofileIntent(){
        this.myProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProfileActivity.startIntent(getContext(), getUser());
            }
        });
    }

    private void setMyVehiclesIntent(){
        this.myVehicleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyVehiclesActivity.startIntent(getContext(), getUser());
            }
        });
    }

    private void setMyPlacesIntent(){
        this.myPlacesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlacesActivity.startIntent(getContext(), getUser());
            }
        });
    }

    private void setMyRentingSpotsIntent(){
        this.myRentingSpotsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRentingSpotsActivity.startIntent(getContext(), getUser());
            }
        });
    }


}
