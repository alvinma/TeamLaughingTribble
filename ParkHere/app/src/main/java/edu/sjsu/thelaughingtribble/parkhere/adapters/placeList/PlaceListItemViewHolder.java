package edu.sjsu.thelaughingtribble.parkhere.adapters.placeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MySpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class PlaceListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context context;
    private TextView addressText;
    private Place place;
    private User user;
    /*
    view is the inflated xml layout
     */
    public PlaceListItemViewHolder(View vehicleListItem) {
        super(vehicleListItem);
        vehicleListItem.setOnClickListener(this);
        this.context = vehicleListItem.getContext();
        this.addressText = (TextView) vehicleListItem.findViewById(R.id.address_text);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getAddressText() {
        return addressText;
    }

    public void setAddressText(TextView addressText) {
        this.addressText = addressText;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void onClick(View v) {
        MySpotsActivity.startIntent(v.getContext(), getUser(), getPlace());
    }
}
