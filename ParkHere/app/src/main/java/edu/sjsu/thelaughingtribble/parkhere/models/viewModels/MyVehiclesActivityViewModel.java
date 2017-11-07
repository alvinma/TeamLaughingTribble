package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 11/2/17.
 */

public class MyVehiclesActivityViewModel extends ActivityCommonViewModel {
    private RecyclerView vehicleList;
    private FloatingActionButton addVehicleButton;


    public MyVehiclesActivityViewModel(Context context) {
        super(context);
        vehicleList = (RecyclerView) ((Activity) context).findViewById(R.id.vehicle_list);
        addVehicleButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.vehicle_add_button);
    }

    public RecyclerView getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(RecyclerView vehicleList) {
        this.vehicleList = vehicleList;
    }

    public FloatingActionButton getAddVehicleButton() {
        return addVehicleButton;
    }

    public void setAddVehicleButton(FloatingActionButton addVehicleButton) {
        this.addVehicleButton = addVehicleButton;
    }
}
