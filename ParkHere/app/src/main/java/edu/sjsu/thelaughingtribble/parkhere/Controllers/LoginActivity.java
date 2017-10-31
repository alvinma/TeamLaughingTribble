package edu.sjsu.thelaughingtribble.parkhere.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {


    LoginViewModel loginUiComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUiComponents = new LoginViewModel(this);
        loginUiComponents.getGoogleLoginButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }

    public void login(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
