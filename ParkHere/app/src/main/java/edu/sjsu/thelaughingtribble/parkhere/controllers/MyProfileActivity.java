package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyProfileActivityViewModel;

public class MyProfileActivity extends AppCompatActivity {

    MyProfileActivityViewModel myProfileActivityUIComponents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myProfileActivityUIComponents = new MyProfileActivityViewModel(this);

        if (myProfileActivityUIComponents.getActionBar() != null) {
            myProfileActivityUIComponents.getActionBar().setTitle("My Profile");
            myProfileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        myProfileActivityUIComponents.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMode(true);
            }
        });

        myProfileActivityUIComponents.getDoneButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMode(false);
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

    private void editMode(boolean edit) {
        if (edit) {
            myProfileActivityUIComponents.getFullName().setVisibility(View.GONE);
            myProfileActivityUIComponents.getPhone().setVisibility(View.GONE);

            myProfileActivityUIComponents.getFullNameEdit().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getPhoneEdit().setVisibility(View.VISIBLE);

            myProfileActivityUIComponents.getEditButton().setVisibility(View.GONE);
            myProfileActivityUIComponents.getDoneButton().setVisibility(View.VISIBLE);
        } else {
            myProfileActivityUIComponents.getFullName().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getPhone().setVisibility(View.VISIBLE);

            myProfileActivityUIComponents.getFullNameEdit().setVisibility(View.GONE);
            myProfileActivityUIComponents.getPhoneEdit().setVisibility(View.GONE);

            myProfileActivityUIComponents.getEditButton().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getDoneButton().setVisibility(View.GONE);
        }
    }

}
