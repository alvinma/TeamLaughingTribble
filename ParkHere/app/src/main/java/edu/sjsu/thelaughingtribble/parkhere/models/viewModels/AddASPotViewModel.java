package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 11/19/17.
 */

public class AddASPotViewModel extends ActivityCommonViewModel {
    private Context context;
    private TextView address;
    private ImageView spotImg;
    private EditText spotNum;
    private EditText price;
    private EditText description;
    private Spinner type;
    private Spinner permitRequired;
    private TextView cancel;
    private FloatingActionButton submit;
    private LinearLayout uploadImg;
    private ProgressBar loading;

    public AddASPotViewModel(Context context) {
        super(context);
        this.address = (TextView) ((Activity) context).findViewById(R.id.address_spot);
        this.spotImg = (ImageView) ((Activity) context).findViewById(R.id.spot_img);
        this.spotNum = (EditText) ((Activity) context).findViewById(R.id.spot_num_edit_text);
        this.price = (EditText) ((Activity) context).findViewById(R.id.price_edit_text);
        this.type = (Spinner) ((Activity) context).findViewById(R.id.spot_type_spinner);
        this.permitRequired = (Spinner) ((Activity) context).findViewById(R.id.permit_spinner);
        this.cancel = (TextView) ((Activity) context).findViewById(R.id.cancel);
        this.submit = (FloatingActionButton) ((Activity) context).findViewById(R.id.submit);
        this.description = (EditText) ((Activity) context).findViewById(R.id.description_edit_text);
        this.uploadImg = (LinearLayout) ((Activity) context).findViewById(R.id.upload_image);
        this.loading = (ProgressBar) ((Activity) context).findViewById(R.id.loading);
    }

    public ProgressBar getLoading() {
        return loading;
    }

    public void setLoading(ProgressBar loading) {
        this.loading = loading;
    }

    public LinearLayout getUploadImg() {
        return uploadImg;
    }

    public void setUploadImg(LinearLayout uploadImg) {
        this.uploadImg = uploadImg;
    }

    public EditText getDescription() {
        return description;
    }

    public void setDescription(EditText description) {
        this.description = description;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getAddress() {
        return address;
    }

    public void setAddress(TextView address) {
        this.address = address;
    }

    public ImageView getSpotImg() {
        return spotImg;
    }

    public void setSpotImg(ImageView spotImg) {
        this.spotImg = spotImg;
    }

    public EditText getSpotNum() {
        return spotNum;
    }

    public void setSpotNum(EditText spotNum) {
        this.spotNum = spotNum;
    }

    public EditText getPrice() {
        return price;
    }

    public void setPrice(EditText price) {
        this.price = price;
    }

    public Spinner getType() {
        return type;
    }

    public void setType(Spinner type) {
        this.type = type;
    }

    public Spinner getPermitRequired() {
        return permitRequired;
    }

    public void setPermitRequired(Spinner permitRequired) {
        this.permitRequired = permitRequired;
    }

    public TextView getCancel() {
        return cancel;
    }

    public void setCancel(TextView cancel) {
        this.cancel = cancel;
    }

    public FloatingActionButton getSubmit() {
        return submit;
    }

    public void setSubmit(FloatingActionButton submit) {
        this.submit = submit;
    }

    public void setUpPermitSpinner(Spinner permitSpinner, Context context){
        //permitSpinner = addASPotUI.getPermitRequired();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.permit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        permitSpinner.setAdapter(adapter);

    }

    public void setUpTypeSpinner(Spinner typeSpinner, Context context){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.spot_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    public void setSpotImageVisibility(boolean visible){
        if(visible){
            this.spotImg.setVisibility(View.VISIBLE);
            this.loading.setVisibility(View.GONE);
        }else {
            this.spotImg.setVisibility(View.GONE);
            this.loading.setVisibility(View.VISIBLE);
        }
    }


}
