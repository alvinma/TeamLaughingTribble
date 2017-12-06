package edu.sjsu.thelaughingtribble.parkhere.adapters.spotList;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class SpotListAdapter extends RecyclerView.Adapter<SpotListItemViewHolder> {

    private ArrayList<Spot> spots = new ArrayList<>();
    private SpotListItemViewHolder spotListUIComponents;
    private User user;
    private String title="";
    private boolean posting=false;
    private String placeId="";

    public SpotListAdapter(ArrayList<Spot> spots, User user) {
        this.spots = spots;
        this.user = user;

    }

    public SpotListAdapter(ArrayList<Spot> spots, User user, String title, String placeID,  boolean posting) {
        this.spots = spots;
        this.user = user;
        this.title = title;
        this.placeId = placeID;
        this.posting = posting;

    }

    @Override
    public void onBindViewHolder(SpotListItemViewHolder holder, int position) {
        Spot spot = spots.get(position);
        spotListUIComponents.setSpot(spot);
        spotListUIComponents.setUser(user);
        spotListUIComponents.getDescriptionText().setText(spot.getDescription());
        spotListUIComponents.getNextAvailableText().setText(spot.getNextAvailable());
        spotListUIComponents.getPermitRequiredText().setText(spot.getPermitRequired());
        spotListUIComponents.getPriceText().setText(String.valueOf(spot.getPrice()));
        spotListUIComponents.getRentingText().setText(spot.getRenting());
        spotListUIComponents.getTypeText().setText(spot.getType());
        spotListUIComponents.getSpotNumberText().setText(spot.getSpotNumber());


        if (spot.getPhoto() != null) {
            Glide.with(spotListUIComponents.getContext()).load(spot.getPhoto()).into(spotListUIComponents.getSpotImage());
        } else {
            spotListUIComponents.getSpotImage().setImageResource(R.drawable.not_available);
        }
    }

    @Override
    public SpotListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_list_item, parent, false);
        if(posting) {
            spotListUIComponents = new SpotListItemViewHolder(mView, title, placeId, posting);
        }else {
            spotListUIComponents = new SpotListItemViewHolder(mView);
        }
        return spotListUIComponents;
    }


    @Override
    public int getItemCount() {
        return spots.size();
    }
}
