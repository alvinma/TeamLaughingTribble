package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;

/**
 * Created by jennifernghinguyen on 11/2/17.
 */

public class MyProfileActivityViewModel extends ActivityCommonViewModel {

    private CircleImageView avarta;
    private TextView email;
    private TextView fullName;
    private TextView phone;
    private FloatingActionButton editButton;
    private FloatingActionButton doneButton;
    private EditText fullNameEdit;
    private EditText phoneEdit;
    public MyProfileActivityViewModel(Context context) {
        super(context);
        avarta = (CircleImageView) ((Activity) context).findViewById(R.id.avarta);
        email = (TextView) ((Activity) context).findViewById(R.id.email_text);
        fullName = (TextView) ((Activity) context).findViewById(R.id.full_name_text);
        phone = (TextView) ((Activity) context).findViewById(R.id.phone_text);
        editButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.edit);
        doneButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.done);
        fullNameEdit = (EditText) ((Activity) context).findViewById(R.id.full_name_edit_text);
        phoneEdit = (EditText) ((Activity) context).findViewById(R.id.phone_edit_text);
    }

    public CircleImageView getAvarta() {
        return avarta;
    }

    public void setAvarta(CircleImageView avarta) {
        this.avarta = avarta;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public TextView getFullName() {
        return fullName;
    }

    public void setFullName(TextView fullName) {
        this.fullName = fullName;
    }

    public TextView getPhone() {
        return phone;
    }

    public void setPhone(TextView phone) {
        this.phone = phone;
    }

    public FloatingActionButton getEditButton() {
        return editButton;
    }

    public void setEditButton(FloatingActionButton editButton) {
        this.editButton = editButton;
    }

    public FloatingActionButton getDoneButton() {
        return doneButton;
    }

    public void setDoneButton(FloatingActionButton doneButton) {
        this.doneButton = doneButton;
    }

    public EditText getFullNameEdit() {
        return fullNameEdit;
    }

    public void setFullNameEdit(EditText fullNameEdit) {
        this.fullNameEdit = fullNameEdit;
    }

    public EditText getPhoneEdit() {
        return phoneEdit;
    }

    public void setPhoneEdit(EditText phoneEdit) {
        this.phoneEdit = phoneEdit;
    }

    public void setAvartaUrl(String url){
        Glide.with(getContext()).load(url).into(getAvarta());

    }

    public boolean isEmptyFields(EditText editText){
        boolean status = false;

        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(Constant.REQUIRE_TEXT);
            status = true;
        }

        return status;
    }

    public void setError(EditText editText, String error){
        editText.setError(error);
    }
}
