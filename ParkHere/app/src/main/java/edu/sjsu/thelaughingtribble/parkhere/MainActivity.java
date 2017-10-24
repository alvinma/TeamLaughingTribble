package edu.sjsu.thelaughingtribble.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_logIn(View v){
        Boolean authentication = authentication(v);
    }

    private Boolean authentication(View v){
        String auth_message;
        if(1 == 1){
            auth_message = "Logging In";
        }
        else{
            auth_message = "Could not authenticate you";
        }
        Toast.makeText(this, auth_message, Toast.LENGTH_SHORT).show();
        return Boolean.TRUE;
    }
}
