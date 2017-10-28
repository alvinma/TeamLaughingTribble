package edu.sjsu.thelaughingtribble.parkhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> tempDB = new ArrayList<Integer>();
    public static List<ParkingPostObject> tempObjectDB= new ArrayList<ParkingPostObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void parkingList(View v){
        Intent intent = new Intent(this, ParkingSpotList.class);
        this.startActivity(intent);
    }

    public void registerParking(View v){
        Intent intent = new Intent(this, CreateParkingSpotListing.class);
        this.startActivity(intent);
    }

}