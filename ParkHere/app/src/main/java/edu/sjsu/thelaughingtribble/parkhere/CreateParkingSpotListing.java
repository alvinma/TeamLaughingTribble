package edu.sjsu.thelaughingtribble.parkhere;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.sjsu.thelaughingtribble.parkhere.controllers.MainActivity;

public class CreateParkingSpotListing extends AppCompatActivity{

    Button submitButton;
    EditText name;
    EditText location;
    EditText description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_parking_spot_listing);

        submitButton = (Button)findViewById(R.id.submitButton);
        name = (EditText)findViewById(R.id.ownerName);
        location = (EditText)findViewById(R.id.spotLocation);
        description = (EditText)findViewById(R.id.description);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity tempDB = new MainActivity();
                //Store new data object here
                for(int i = 0; i < 99; i++){
                    if(!tempDB.tempDB.contains(i)){
                        ParkingPostObject objectPost = new ParkingPostObject();
                        tempDB.tempDB.add(i);
                        objectPost.setParkingPost(i, name.getText().toString(), location.getText().toString(), description.getText().toString());
                        tempDB.tempObjectDB.add(objectPost);
                        break;
                    }
                }

                finish();
            }
        });
    }

}
