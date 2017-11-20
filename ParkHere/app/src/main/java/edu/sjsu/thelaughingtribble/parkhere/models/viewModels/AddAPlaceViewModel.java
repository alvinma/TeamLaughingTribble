package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 11/19/17.
 */

public class AddAPlaceViewModel extends  ActivityCommonViewModel {

    private EditText address;
    private TextView cancel;
    private FloatingActionButton submit;

    public AddAPlaceViewModel(Context context){
        super(context);
        this.address = (EditText) ((Activity) context).findViewById(R.id.address_edit_text);
        this.cancel = (TextView) ((Activity) context).findViewById(R.id.cancel_place);
        this.submit = (FloatingActionButton) ((Activity) context).findViewById(R.id.submit_place);
    }

    public EditText getAddress() {
        return address;
    }

    public void setAddress(EditText address) {
        this.address = address;
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
}
