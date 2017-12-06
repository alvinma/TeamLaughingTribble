package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyRentingSpotsActivityViewModel;

public class MyRentingSpotsActivity extends AppCompatActivity {
    MyRentingSpotsActivityViewModel myRentingSpotsActivityUIComponents;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_renting_spots);
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        myRentingSpotsActivityUIComponents = new MyRentingSpotsActivityViewModel(this);
        myRentingSpotsActivityUIComponents.setUser(user);
        if (myRentingSpotsActivityUIComponents.getActionBar() != null) {
            myRentingSpotsActivityUIComponents.getActionBar().setTitle("My Renting Spot");
            myRentingSpotsActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
        Intent intent = new Intent(context, MyRentingSpotsActivity.class);
        Log.i("my places startIntent", user.getUid() + " " + user.getEmail());
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
