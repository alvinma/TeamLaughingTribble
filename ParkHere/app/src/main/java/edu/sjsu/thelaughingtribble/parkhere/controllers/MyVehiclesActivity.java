package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList.HomePostListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.adapters.vehicleList.VehicleListAdapter;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyVehiclesActivityViewModel;

public class MyVehiclesActivity extends AppCompatActivity {
    MyVehiclesActivityViewModel myVehiclesActivityUIComponents;
    private RecyclerView.Adapter adapter;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicles);
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);


        vehicles.add(new Vehicle("WAUFFAFM3CA000000", "Toyota", "Corrola", "2013", "White", "ABC1233", "https://www.enterprise.com/content/dam/global-vehicle-images/cars/VAUX_INSI_2014.png" ));
        if(myVehiclesActivityUIComponents==null) {
            myVehiclesActivityUIComponents = new MyVehiclesActivityViewModel(this);
            myVehiclesActivityUIComponents.setUser(user);
        }
        if(myVehiclesActivityUIComponents.getActionBar()!=null){
            myVehiclesActivityUIComponents.getActionBar().setTitle("My Vehicles");
            myVehiclesActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }


        myVehiclesActivityUIComponents.getVehicleList().setLayoutManager(myVehiclesActivityUIComponents.getLayoutManager());

        adapter = new VehicleListAdapter(vehicles);
        myVehiclesActivityUIComponents.getVehicleList().setAdapter(adapter);

        myVehiclesActivityUIComponents.getAddVehicleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myVehiclesActivityUIComponents.getContext(), AddAVehicle.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MyVehiclesActivity.class);
        Log.i("my vehicle startIntent", user.getUid() + " " + user.getEmail());
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
