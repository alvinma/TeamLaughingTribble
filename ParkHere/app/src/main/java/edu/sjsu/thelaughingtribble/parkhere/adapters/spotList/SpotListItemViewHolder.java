package edu.sjsu.thelaughingtribble.parkhere.adapters.spotList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MySpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.SpotDetailActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class SpotListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context context;
    private TextView spotNumberText;
    private TextView typeText;
    private TextView priceText;
    private TextView permitRequiredText;
    private TextView rentingText;
    private TextView nextAvailableText;
    private TextView descriptionText;
    private ImageView spotImage;
    private User user;
    private Spot spot;


    /*
    view is the inflated xml layout
     */
    public SpotListItemViewHolder(View spotsListItem)  {
        super(spotsListItem);
        this.context = spotsListItem.getContext();
        spotNumberText = (TextView) spotsListItem.findViewById(R.id.spot_number_text);
        typeText = (TextView) spotsListItem.findViewById(R.id.type_text);
        priceText = (TextView) spotsListItem.findViewById(R.id.price_text);
        permitRequiredText = (TextView) spotsListItem.findViewById(R.id.permit_required_text);
        rentingText = (TextView) spotsListItem.findViewById(R.id.renting_text);
        nextAvailableText = (TextView) spotsListItem.findViewById(R.id.next_available_text);
        descriptionText = (TextView) spotsListItem.findViewById(R.id.description_text);
        spotImage = (ImageView) spotsListItem.findViewById(R.id.spot_image);
        spotsListItem.setOnClickListener(this);
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

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getSpotNumberText() {
        return spotNumberText;
    }

    public void setSpotNumberText(TextView spotNumberText) {
        this.spotNumberText = spotNumberText;
    }

    public TextView getTypeText() {
        return typeText;
    }

    public void setTypeText(TextView typeText) {
        this.typeText = typeText;
    }

    public TextView getPriceText() {
        return priceText;
    }

    public void setPriceText(TextView priceText) {
        this.priceText = priceText;
    }

    public TextView getPermitRequiredText() {
        return permitRequiredText;
    }

    public void setPermitRequiredText(TextView permitRequiredText) {
        this.permitRequiredText = permitRequiredText;
    }

    public TextView getRentingText() {
        return rentingText;
    }

    public void setRentingText(TextView rentingText) {
        this.rentingText = rentingText;
    }

    public TextView getNextAvailableText() {
        return nextAvailableText;
    }

    public void setNextAvailableText(TextView nextAvailableText) {
        this.nextAvailableText = nextAvailableText;
    }

    public TextView getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(TextView descriptionText) {
        this.descriptionText = descriptionText;
    }

    public ImageView getSpotImage() {
        return spotImage;
    }

    public void setSpotImage(ImageView spotImage) {
        this.spotImage = spotImage;
    }


    @Override
    public void onClick(View v) {
        Log.i("SpotListItemViewHolder", "onclick");
        SpotDetailActivity.startIntent(v.getContext(), getUser(), getSpot());
    }
}
