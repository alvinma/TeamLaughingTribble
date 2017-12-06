package edu.sjsu.thelaughingtribble.parkhere;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MainActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MySpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

public class ViewObjectInfo extends AppCompatActivity {

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
        name = (TextView) findViewById(R.id.name);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);
        parkingImage = (ImageView) findViewById(R.id.parkingImage);
        purchaseButton = (Button) findViewById(R.id.purchaseButton);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        }

        name.setText("Owner's Name : " + tempObjectDB.tempObjectDB.get(position).getOwnerName());
        location.setText("Address : " + tempObjectDB.tempObjectDB.get(position).getAddressLocation());
        description.setText("Description : " + tempObjectDB.tempObjectDB.get(position).getDescription());
        parkingImage.setImageResource(getResources().getIdentifier("parkingimage", "drawable", getPackageName()));

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testToastFunction();
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        testToastFunction();
    }

//    public static void startIntent(Context context) {
//        Intent intent = new Intent(context, PostInformationActivity.class);
//        context.startActivity(intent);
//    }

    //Call this function to test the view post information
    //testing: Runs whenever the user clicks on the post to view more details
    public void testToastFunction(){
        Toast.makeText(getBaseContext(),"ViewObjectInfo:::!!!!!", Toast.LENGTH_SHORT).show();
    }

}
