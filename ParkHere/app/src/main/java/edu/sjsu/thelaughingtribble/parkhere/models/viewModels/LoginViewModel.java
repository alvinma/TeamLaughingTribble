package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 10/30/17.
 */

public  class LoginViewModel {

    private Button googleLoginButton;

    public LoginViewModel(Context context){
        this.googleLoginButton = (Button) ((Activity) context).findViewById(R.id.google_login_button);;
    }

    public Button getGoogleLoginButton(){
        return this.googleLoginButton;
    }

}
