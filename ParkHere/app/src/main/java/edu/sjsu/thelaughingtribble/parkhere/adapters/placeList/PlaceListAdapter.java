package edu.sjsu.thelaughingtribble.parkhere.adapters.placeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList.VehicleListItemViewHolder;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MySpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListItemViewHolder> {

    private ArrayList<Place> places = new ArrayList<>();
    private PlaceListItemViewHolder placeListUIComponents;
    private User user;

    public PlaceListAdapter(ArrayList<Place> places, User user){
        this.places = places;
        this.user = user;
    }

    @Override
    public void onBindViewHolder(PlaceListItemViewHolder holder, int position) {
        Place place = places.get(position);
        placeListUIComponents.setPlace(place);
        placeListUIComponents.setUser(user);
        placeListUIComponents.getAddressText().setText(place.getAddress());

    }

    @Override
    public PlaceListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        placeListUIComponents = new PlaceListItemViewHolder(mView);
        return placeListUIComponents;
    }



    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setPlacesList(ArrayList<Place> data){
        places = data;
        notifyDataSetChanged();
    }


}
