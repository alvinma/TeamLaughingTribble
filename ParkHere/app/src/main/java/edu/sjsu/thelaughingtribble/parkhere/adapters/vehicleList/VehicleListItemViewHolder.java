package edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class VehicleListItemViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView vehicleName;
    private TextView vin;
    private TextView color;
    private TextView plateNumber;
    private ImageView vehicleImage;

    /*
    view is the inflated xml layout
     */
    public VehicleListItemViewHolder(View vehicleListItem) {
        super(vehicleListItem);
        this.context = vehicleListItem.getContext();
        vehicleName = (TextView) vehicleListItem.findViewById(R.id.vehicle_name_text);
        vin = (TextView) vehicleListItem.findViewById(R.id.vin_text);
        color = (TextView) vehicleListItem.findViewById(R.id.color_text);
        plateNumber = (TextView) vehicleListItem.findViewById(R.id.plate_number_text);
        vehicleImage = (ImageView) vehicleListItem.findViewById(R.id.vehicle_image);

    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ImageView getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(ImageView vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public TextView getVehicleName() {

        return vehicleName;
    }

    public void setVehicleName(TextView vehicle_name) {
        this.vehicleName = vehicle_name;
    }

    public TextView getVin() {
        return vin;
    }

    public void setVin(TextView vin) {
        this.vin = vin;
    }

    public TextView getColor() {
        return color;
    }

    public void setColor(TextView color) {
        this.color = color;
    }

    public TextView getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(TextView plate_number) {
        this.plateNumber = plate_number;
    }
}
