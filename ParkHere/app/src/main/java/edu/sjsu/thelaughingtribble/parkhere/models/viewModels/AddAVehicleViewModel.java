package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 11/4/17.
 */

public class AddAVehicleViewModel extends ActivityCommonViewModel {
    private LinearLayout uploadLayout;
    private EditText brandEditText;
    private EditText makeEditTextl;
    private EditText yearEditText;
    private EditText colorEditText;
    private EditText vinEditText;
    private TextView cancelTextView;
    private FloatingActionButton submitButton;

    public AddAVehicleViewModel(Context context) {
        super(context);
        uploadLayout = (LinearLayout) ((Activity) context).findViewById(R.id.upload_image);
        brandEditText = (EditText) ((Activity) context).findViewById(R.id.brand_edit_text);
        makeEditTextl = (EditText) ((Activity) context).findViewById(R.id.make_edit_text);
        yearEditText = (EditText) ((Activity) context).findViewById(R.id.year_edit_text);
        colorEditText = (EditText) ((Activity) context).findViewById(R.id.color_edit_text);
        vinEditText = (EditText) ((Activity) context).findViewById(R.id.vin_edit_text);
        cancelTextView = (TextView) ((Activity) context).findViewById(R.id.cancel);
        submitButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.post_vehicle);
    }

    public FloatingActionButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(FloatingActionButton submitButton) {
        this.submitButton = submitButton;
    }

    public LinearLayout getUploadLayout() {
        return uploadLayout;
    }

    public void setUploadLayout(LinearLayout uploadLayout) {
        this.uploadLayout = uploadLayout;
    }

    public EditText getBrandEditText() {
        return brandEditText;
    }

    public void setBrandEditText(EditText brandEditText) {
        this.brandEditText = brandEditText;
    }

    public EditText getMakeEditTextl() {
        return makeEditTextl;
    }

    public void setMakeEditTextl(EditText makeEditTextl) {
        this.makeEditTextl = makeEditTextl;
    }

    public EditText getYearEditText() {
        return yearEditText;
    }

    public void setYearEditText(EditText yearEditText) {
        this.yearEditText = yearEditText;
    }

    public EditText getColorEditText() {
        return colorEditText;
    }

    public void setColorEditText(EditText colorEditText) {
        this.colorEditText = colorEditText;
    }

    public EditText getVinEditText() {
        return vinEditText;
    }

    public void setVinEditText(EditText vinEditText) {
        this.vinEditText = vinEditText;
    }

    public TextView getCancelTextView() {
        return cancelTextView;
    }

    public void setCancelTextView(TextView cancelTextView) {
        this.cancelTextView = cancelTextView;
    }
}
