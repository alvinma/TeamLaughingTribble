package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.AddAPlaceViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NavigationViewModel;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.NotificationActivityViewModel;

public class AddAPlace extends AppCompatActivity {

    private AddAPlaceViewModel addAPlaceUI;
    private User user;
    private DatabaseReference database;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aplace);
        init();
        addAPlace();
        cancel();
    }

    private void addAPlace(){
        addAPlaceUI.getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!addAPlaceUI.getAddress().getText().equals("")){

                    Log.i("Add a place onclick", user.getUid() + " " + user.getEmail());
                    String key = database.child("places").child(user.getUid()).push().getKey();
                    Place place = new Place(key, addAPlaceUI.getAddress().getText().toString().trim());
                    Log.i("key", key);
                    database.child("places/"+user.getUid()+"/"+key).setValue(place);

                    MyPlacesActivity.startIntent(AddAPlace.this, user);
                }else {
                    addAPlaceUI.getAddress().setError(Constant.REQUIRE_TEXT);
                }
            }
        });
    }
    private void cancel(){
        addAPlaceUI.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void init(){
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        Log.i("Add a place", user.getUid() + " " + user.getEmail());
        addAPlaceUI = new AddAPlaceViewModel(this);
       addAPlaceUI.setUser(user);
        addAPlaceUI.getActionBar().setDisplayHomeAsUpEnabled(true);
        addAPlaceUI.getActionBar().setTitle("Add A Place");
    }
    public static void startIntent(Context context, User user){
        Intent intent = new Intent(context, AddAPlace.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
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
