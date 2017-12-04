package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 12/3/17.
 */

public class SpotDetailViewModel extends ActivityCommonViewModel {


    private TextView spotNumberText;
    private TextView typeText;
    private TextView priceText;
    private TextView permitRequiredText;
    private TextView rentingText;
    private TextView nextAvailableText;
    private TextView descriptionText;
    private ImageView spotImage;
    public SpotDetailViewModel(Context context) {
        super(context);

        spotNumberText = (TextView) ((Activity) context).findViewById(R.id.spot_number_text);
        typeText = (TextView) ((Activity) context).findViewById(R.id.type_text);
        priceText = (TextView) ((Activity) context).findViewById(R.id.price_text);
        permitRequiredText = (TextView) ((Activity) context).findViewById(R.id.permit_required_text);
        rentingText = (TextView) ((Activity) context).findViewById(R.id.renting_text);
        nextAvailableText = (TextView) ((Activity) context).findViewById(R.id.next_available_text);
        descriptionText = (TextView) ((Activity) context).findViewById(R.id.description_text);
        spotImage = (ImageView) ((Activity) context).findViewById(R.id.spot_image);
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
