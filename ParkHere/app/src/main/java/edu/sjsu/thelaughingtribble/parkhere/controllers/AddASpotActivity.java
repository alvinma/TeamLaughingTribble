package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.AddASPotViewModel;

import static android.R.attr.key;

public class AddASpotActivity extends AppCompatActivity {

    private AddASPotViewModel addASPotUI;
    private Place place = null;
    private StorageReference firebaseStorage;
    private User user;
    private Uri uri;
    private DatabaseReference database;

    private String type;
    private String description;
    private double price;
    private String permitRequired;
    private String spotNumber;
    private String photo = null;
    private String renting;
    private String nextAvailable;
    private static final int GALLLERY_INTENT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aspot);

        if (addASPotUI == null) {
            addASPotUI = new AddASPotViewModel(this);
            addASPotUI.getActionBar().setDisplayHomeAsUpEnabled(true);
            addASPotUI.getActionBar().setTitle("Add A Spot");
        }

        init();
        submit();

    }


    private void submit() {
        addASPotUI.getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addASPotUI.getSpotNum().getText().equals("")) {
                    spotNumber = addASPotUI.getSpotNum().getText().toString();
                } else {
                    addASPotUI.getSpotNum().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getPrice().getText().equals("")) {
                    price = Double.valueOf(addASPotUI.getPrice().getText().toString());
                } else {
                    addASPotUI.getSpotNum().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getDescription().getText().equals("")) {
                    description = addASPotUI.getDescription().getText().toString();
                } else {
                    addASPotUI.getSpotNum().setError(Constant.REQUIRE_TEXT);
                }

                renting = "no";
                nextAvailable = Utilities.getNextDateAvailable();

                Log.i("address", place.getAddress());
                Spot spot = null;
                String key = database.child("spots").child(user.getUid()).push().getKey();
                if (photo == null) {
                    spot = new Spot(place.getAddress(), type, description, price, permitRequired, spotNumber, renting, nextAvailable, key);
                } else {
                    spot = new Spot(place.getAddress(), type, description, price, permitRequired, spotNumber, renting, nextAvailable, key, photo);
                }
                database.child("spots/"+user.getUid()+"/"+key).setValue(spot);

                MySpotsActivity.startIntent(AddASpotActivity.this, user, place);
            }
        });
    }

    public void init() {
        database = FirebaseDatabase.getInstance().getReference();
        setFirebaseStorage();
        getDataFromIntent();
        setUpPermitSpinner();
        setUpTypeSpinner();
        setUploadImageListener();
    }

    private void setUploadImageListener() {
        addASPotUI.getUploadImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLLERY_INTENT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLLERY_INTENT_CODE && resultCode == RESULT_OK) {
            addASPotUI.setSpotImageVisibility(false);
            uri = data.getData();
            StorageReference path = firebaseStorage.child("Photos").child(uri.getLastPathSegment());
            path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //
                    StorageReference pathReference = firebaseStorage.child("Photos/" + uri.getLastPathSegment());
                    getDownLoadUrl(pathReference);
                    Toast.makeText(AddASpotActivity.this, pathReference.toString(), Toast.LENGTH_LONG).show();
                    addASPotUI.setSpotImageVisibility(true);
                    //addASPotUI.setSpotImg(pathReference);
                    Log.i("image path", pathReference.toString());


                }
            });
        }
    }

    private void getDownLoadUrl(StorageReference storage) {
        storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("download url", uri.toString());
                photo = uri.toString();
                Glide.with(AddASpotActivity.this)
                        .load(uri.toString())
                        .into(addASPotUI.getSpotImg());
            }
        });
    }

    private void setFirebaseStorage() {
        firebaseStorage = FirebaseStorage.getInstance().getReference();
    }

    private void getDataFromIntent() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        place = (Place) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_PLACE);

        addASPotUI.setPlace(place);
        addASPotUI.setUser(user);
        addASPotUI.getAddress().setText(place.getAddress());
    }

    private void setUpPermitSpinner() {

        addASPotUI.setUpPermitSpinner(addASPotUI.getPermitRequired(), this);
        addASPotUI.getPermitRequired().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* Toast.makeText(parent.getContext(),
                        "Selecting " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();*/

                permitRequired = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setUpTypeSpinner() {

        addASPotUI.setUpTypeSpinner(addASPotUI.getType(), this);
        addASPotUI.getType().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(parent.getContext(),
                        "Selecting " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();*/

                type = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static void startIntent(Context context, User user, Place place) {
        Log.i("intent to add spot ", place.getAddress());
        Log.i("intent to add spot ", user.getUid() + " " + user.getEmail());
        Intent intent = new Intent(context, AddASpotActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
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
