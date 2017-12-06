package edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListItemViewHolder> {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private VehicleListItemViewHolder vehicleListUIComponents;

    public VehicleListAdapter(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void onBindViewHolder(VehicleListItemViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);

        vehicleListUIComponents.getVehicleName().setText(vehicle.getVehicleFullName());
        vehicleListUIComponents.getVin().setText(vehicle.getVin());
        vehicleListUIComponents.getColor().setText(vehicle.getColor());
        vehicleListUIComponents.getPlateNumber().setText(vehicle.getPlateNumber());

        if (vehicle.getPhoto() != null) {
            Glide.with(vehicleListUIComponents.getContext()).load(vehicle.getPhoto()).into(vehicleListUIComponents.getVehicleImage());
        } else {
            vehicleListUIComponents.getVehicleImage().setImageResource(R.drawable.not_available);
        }
    }

    @Override
    public VehicleListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        vehicleListUIComponents = new VehicleListItemViewHolder(mView);
        return vehicleListUIComponents;
    }


    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
