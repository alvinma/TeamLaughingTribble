package edu.sjsu.thelaughingtribble.parkhere.adapters.spotList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class SpotListItemViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView spotNumberText;
    private TextView typeText;
    private TextView priceText;
    private TextView permitRequiredText;
    private TextView rentingText;
    private TextView nextAvailableText;
    private TextView descriptionText;
    private ImageView spotImage;


    /*
    view is the inflated xml layout
     */
    public SpotListItemViewHolder(View vehicleListItem) {
        super(vehicleListItem);
        this.context = vehicleListItem.getContext();
        spotNumberText = (TextView) vehicleListItem.findViewById(R.id.spot_number_text);
        typeText = (TextView) vehicleListItem.findViewById(R.id.type_text);
        priceText = (TextView) vehicleListItem.findViewById(R.id.price_text);
        permitRequiredText = (TextView) vehicleListItem.findViewById(R.id.permit_required_text);
        rentingText = (TextView) vehicleListItem.findViewById(R.id.renting_text);
        nextAvailableText = (TextView) vehicleListItem.findViewById(R.id.next_available_text);
        descriptionText = (TextView) vehicleListItem.findViewById(R.id.description_text);
        spotImage = (ImageView) vehicleListItem.findViewById(R.id.spot_image);
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
}
