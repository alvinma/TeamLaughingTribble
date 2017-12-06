package edu.sjsu.thelaughingtribble.parkhere.adapters.placeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListItemViewHolder> {

    private ArrayList<Place> places = new ArrayList<>();
    private PlaceListItemViewHolder placeListUIComponents;
    private User user;
    private boolean posting = false;
    private String title = "";

    public PlaceListAdapter(ArrayList<Place> places, User user) {
        this.places = places;
        this.user = user;
    }
    public PlaceListAdapter(ArrayList<Place> places, User user, String title, boolean posting) {
        this.places = places;
        this.user = user;
        this.title = title;
        this.posting = posting;
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
       if(posting){
           placeListUIComponents = new PlaceListItemViewHolder(mView, title, posting);
       }else {
           placeListUIComponents = new PlaceListItemViewHolder(mView);
       }

        return placeListUIComponents;
    }


    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setPlacesList(ArrayList<Place> data) {
        places = data;
        notifyDataSetChanged();
    }


}
