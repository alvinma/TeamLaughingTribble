package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.SpotDetailViewModel;

public class SpotDetailActivity extends AppCompatActivity {

    private Spot spot;
    private User user;
    private SpotDetailViewModel spotDetailUI;
    private boolean deleteStatus = false;
    private Place place;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public static void startIntent(Context context, User user, Spot spot) {
        Log.i("startIntent detail spot", "click");
        Intent intent = new Intent(context, SpotDetailActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.INTENT_EXTRA_SPOT, spot);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        setup();
        getIntentData();
        initView();
    }

    private void setup() {
        spotDetailUI = new SpotDetailViewModel(this);
        database = FirebaseDatabase.getInstance();
        //setSupportActionBar(spotDetailUI.getToolbar());
    }

    private void initView() {
        if (spot.getPhoto() != null) {
            Glide.with(this).load(spot.getPhoto()).into(spotDetailUI.getSpotImage());
        }
        spotDetailUI.getDescriptionText().setText(spot.getDescription());
        spotDetailUI.getNextAvailableText().setText(spot.getNextAvailable());
        spotDetailUI.getPriceText().setText(String.valueOf(spot.getPrice()));
        spotDetailUI.getPermitRequiredText().setText(spot.getPermitRequired());
        spotDetailUI.getTypeText().setText(spot.getType());
        spotDetailUI.getRentingText().setText(spot.getRenting());
        spotDetailUI.getSpotNumberText().setText(spot.getSpotNumber());


    }

    private void getIntentData() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        spot = (Spot) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_SPOT);
        place = new Place(spot.getAddress(), spot.getFirebasePlaceKey());
    }

    private void deleteSpot() {
        //delete
        Log.i("getSpotId", spot.getSpotId());
        reference = database.getReference("spots/" + user.getUid()).child(spot.getSpotId());
        reference.removeValue();


        database.getReference("spots/" + user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(spot.getSpotId())) {
                    showSuccessfullMessage();
                }else {
                    showFailedMessage();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //show success dialog

    }

    private void showSuccessfullMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SpotDetailActivity.this);
        builder.setMessage(Constant.DELETE_DIALOG_SUCC_MESS)
                .setCancelable(false)
                .setPositiveButton(Constant.DELETE_DIALOG_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySpotsActivity.startIntent(getBaseContext(), user, place);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setTitle(Constant.DELETE_DIALOG_SUCCESS);
        dialog.show();
    }

    private void showFailedMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SpotDetailActivity.this);
        builder.setMessage(Constant.DELETE_DIALOG_FAIL_MESS)
                .setCancelable(false)
                .setPositiveButton(Constant.DELETE_DIALOG_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySpotsActivity.startIntent(getBaseContext(), user, place);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setTitle(Constant.ERROR);
        dialog.show();
    }
    private void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SpotDetailActivity.this);
        builder.setMessage(Constant.DELETE_DIALOG_MESS)
                .setCancelable(false)
                .setPositiveButton(Constant.DELETE_DIALOG_YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSpot();
                    }
                })
                .setNegativeButton(Constant.DELETE_DIALOG_NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteStatus = false;
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setTitle(Constant.DELETE_DIALOG_TITLE);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.spot_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_spot:
                AddASpotActivity.startIntent(getApplicationContext(), user, place, spot);
                return true;
            case R.id.delete_spot:
                confirmDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}