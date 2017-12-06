package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.AddAVehicleViewModel;

public class AddAVehicle extends AppCompatActivity {
    private static final int GALLLERY_INTENT_CODE = 1;
    String brand = null;
    String make = null;
    String year = null;
    String color = null;
    String vin = null;
    String photo = null;
    String plateNumber = null;
    private AddAVehicleViewModel addAVehicleUIComponents;
    private StorageReference firebaseStorage;
    private DatabaseReference database;
    private Uri uri;
    private User user;

    public static void startIntent(Context context, User user) {
        Log.i("intent to add spot ", user.getUid() + " " + user.getEmail());
        Intent intent = new Intent(context, AddAVehicle.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avehicle);
        init();
        submit();
    }

    private void init() {
        database = FirebaseDatabase.getInstance().getReference();
        addAVehicleUIComponents = new AddAVehicleViewModel(this);
        addAVehicleUIComponents.getActionBar().setTitle("Add a Vehicle");
        addAVehicleUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        addAVehicleUIComponents.getCancelTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDataFromIntent();
        setFirebaseStorage();
        setUploadImageListener();

    }

    private void setFirebaseStorage() {
        firebaseStorage = FirebaseStorage.getInstance().getReference();
    }

    private void submit() {
        addAVehicleUIComponents.getSubmitButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addAVehicleUIComponents.getBrandEditText().getText().toString().equals("")) {
                    brand = addAVehicleUIComponents.getBrandEditText().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getBrandEditText().setError(Constant.REQUIRE_TEXT);
                }

                if (!addAVehicleUIComponents.getMakeEditTextl().getText().toString().equals("")) {
                    make = addAVehicleUIComponents.getMakeEditTextl().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getMakeEditTextl().setError(Constant.REQUIRE_TEXT);
                }

                if (!addAVehicleUIComponents.getYearEditText().getText().toString().equals("")) {
                    year = addAVehicleUIComponents.getYearEditText().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getYearEditText().setError(Constant.REQUIRE_TEXT);
                }

                if (!addAVehicleUIComponents.getColorEditText().getText().toString().equals("")) {
                    color = addAVehicleUIComponents.getColorEditText().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getColorEditText().setError(Constant.REQUIRE_TEXT);
                }

                if (!addAVehicleUIComponents.getVinEditText().getText().toString().equals("")) {
                    vin = addAVehicleUIComponents.getVinEditText().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getVinEditText().setError(Constant.REQUIRE_TEXT);
                }

                if (!addAVehicleUIComponents.getPlateEditText().getText().toString().equals("")) {
                    plateNumber = addAVehicleUIComponents.getPlateEditText().getText().toString().trim();
                } else {
                    addAVehicleUIComponents.getPlateEditText().setError(Constant.REQUIRE_TEXT);
                }

                if (vin != null && brand != null && make != null && year != null && color != null && plateNumber != null) {
                    Vehicle vehicle = null;

                    if (photo == null) {
                        vehicle = new Vehicle(vin, brand, make, year, color, plateNumber);
                    } else {
                        vehicle = new Vehicle(vin, brand, make, year, color, plateNumber, photo);
                    }
                    String key = database.child("vehicles").child(user.getUid()).push().getKey();
                    database.child("vehicles/" + user.getUid() + "/" + key).setValue(vehicle);

                    MyVehiclesActivity.startIntent(AddAVehicle.this, user);
                }

            }
        });
    }


    private void setUploadImageListener() {
        addAVehicleUIComponents.getUploadLayout().setOnClickListener(new View.OnClickListener() {
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
            addAVehicleUIComponents.setVehicleImageVisibility(false);
            uri = data.getData();
            StorageReference path = firebaseStorage.child("Photos/" + user.getUid() + "/vehicles").child(uri.getLastPathSegment());
            path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //
                    StorageReference pathReference = firebaseStorage.child("Photos/" + user.getUid() + "/vehicles/" + uri.getLastPathSegment());
                    getDownLoadUrl(pathReference);
                    //Toast.makeText(AddASpotActivity.this, pathReference.toString(), Toast.LENGTH_LONG).show();
                    addAVehicleUIComponents.setVehicleImageVisibility(true);
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
                Glide.with(AddAVehicle.this)
                        .load(uri.toString())
                        .into(addAVehicleUIComponents.getVehicleImg());
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
    private void getDataFromIntent() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        addAVehicleUIComponents.setUser(user);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               MyVehiclesActivity.startIntent(this, user);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
