package edu.sjsu.thelaughingtribble.parkhere;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.controllers.MainActivity;

public class ViewObjectInfo extends AppCompatActivity{

    TextView name;
    TextView location;
    TextView description;
    ImageView parkingImage;
    Button purchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_object_info);

        MainActivity tempObjectDB = new MainActivity();
        name = (TextView)findViewById(R.id.name);
        location = (TextView)findViewById(R.id.location);
        description = (TextView)findViewById(R.id.description);
        parkingImage = (ImageView)findViewById(R.id.parkingImage);
        purchaseButton = (Button)findViewById(R.id.purchaseButton);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            position = extras.getInt("position");
        }

        name.setText("Owner's Name : " + tempObjectDB.tempObjectDB.get(position).getOwnerName());
        location.setText("Address : " + tempObjectDB.tempObjectDB.get(position).getAddressLocation());
        description.setText("Description : " + tempObjectDB.tempObjectDB.get(position).getDescription());
        parkingImage.setImageResource(getResources().getIdentifier("parkingimage", "drawable", getPackageName()));

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }
        });

    }

}
