package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.AddAVehicleViewModel;

public class AddAVehicle extends AppCompatActivity {
    private AddAVehicleViewModel addAVehicleUIComponents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avehicle);
        if(addAVehicleUIComponents == null){
            addAVehicleUIComponents = new AddAVehicleViewModel(this);
        }
        addAVehicleUIComponents.getActionBar().setTitle("Add a Vehicle");
        addAVehicleUIComponents.getCancelTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

}
