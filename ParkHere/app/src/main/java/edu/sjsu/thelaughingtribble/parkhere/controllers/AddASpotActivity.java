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
    private Spot edit_data;

    private User user;
    private Uri uri;
    private StorageReference firebaseStorage;
    private DatabaseReference database;

    private String type;
    private String description;
    private double price = 0;
    private String permitRequired;
    private String spotNumber;
    private String photo = null;
    private String renting;
    private String nextAvailable;
    private static final int GALLLERY_INTENT_CODE = 1;
    private boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aspot);

        if (addASPotUI == null) {
            addASPotUI = new AddASPotViewModel(this);
            addASPotUI.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();


    }


    private void submitNewSpot() {

        addASPotUI.getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!addASPotUI.getSpotNum().getText().toString().equals("")) {
                    spotNumber = addASPotUI.getSpotNum().getText().toString();
                } else {
                    addASPotUI.getSpotNum().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getPrice().getText().toString().equals("")) {
                    price = Double.valueOf(addASPotUI.getPrice().getText().toString());
                } else {
                    addASPotUI.getPrice().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getDescription().getText().toString().equals("")) {
                    description = addASPotUI.getDescription().getText().toString();
                } else {
                    addASPotUI.getDescription().setError(Constant.REQUIRE_TEXT);
                }

                if (place.getAddress() != null && type != null && description != null && price != 0 && permitRequired != null && spotNumber != null && place.getFirebaseKey() != null) {
                    renting = "yes";
                    nextAvailable = Utilities.getNextDateAvailable();
                    Spot spot = null;
                    String key = database.child("spots").child(user.getUid()).push().getKey();
                    if (photo == null) {
                        spot = new Spot(place.getAddress(), type, description, price, permitRequired, spotNumber, renting, nextAvailable, place.getFirebaseKey(), key);
                    } else {
                        spot = new Spot(place.getAddress(), type, description, price, permitRequired, spotNumber, renting, nextAvailable, place.getFirebaseKey(), key, photo);
                    }

                    database.child("spots/" + user.getUid() + "/" + key).setValue(spot);

                    MySpotsActivity.startIntent(AddASpotActivity.this, user, place);

                }
            }
        });
    }

    private void updateSpot() {
        addASPotUI.getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!addASPotUI.getSpotNum().getText().toString().equals("")) {
                    spotNumber = addASPotUI.getSpotNum().getText().toString();
                } else {
                    addASPotUI.getSpotNum().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getPrice().getText().toString().equals("")) {
                    price = Double.valueOf(addASPotUI.getPrice().getText().toString());
                } else {
                    addASPotUI.getPrice().setError(Constant.REQUIRE_TEXT);
                }

                if (!addASPotUI.getDescription().getText().toString().equals("")) {
                    description = addASPotUI.getDescription().getText().toString();
                } else {
                    addASPotUI.getDescription().setError(Constant.REQUIRE_TEXT);
                }

                if (place.getAddress() != null && type != null && description != null && price != 0 && permitRequired != null && spotNumber != null && place.getFirebaseKey() != null && edit_data.getSpotId()!=null) {

                    renting = edit_data.getRenting();
                    nextAvailable = edit_data.getNextAvailable();
                    Spot spot = null;
                   // database.child("spots").child(user.getUid()).child(edit_data.getSpotId());

                    spot = new Spot(place.getAddress(), type, description, price, permitRequired, spotNumber, renting, nextAvailable, place.getFirebaseKey(), edit_data.getSpotId(), photo);


                    database.child("spots/" + user.getUid() + "/" + edit_data.getSpotId()).setValue(spot);

                    MySpotsActivity.startIntent(AddASpotActivity.this, user, place);

                }
            }
        });
    }

    public void init() {
        database = FirebaseDatabase.getInstance().getReference();
        setFirebaseStorage();
        getDataFromIntent();
        setUpUI();

        if(edit){
            updateSpot();
        }else {
            submitNewSpot();
        }

        addASPotUI.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
            StorageReference path = firebaseStorage.child("Photos/" + user.getUid() + "/spots").child(uri.getLastPathSegment());
            path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageReference pathReference = firebaseStorage.child("Photos/" + user.getUid() + "/spots/" + uri.getLastPathSegment());
                    getDownLoadUrl(pathReference);
                    Toast.makeText(AddASpotActivity.this, pathReference.toString(), Toast.LENGTH_LONG).show();
                    addASPotUI.setSpotImageVisibility(true);
                }
            });
        }
    }

    private void getDownLoadUrl(StorageReference storage) {
        storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
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
        if ((Spot) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_SPOT) != null) {
            edit_data = (Spot) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_SPOT);
            edit = true;
        }


        addASPotUI.setPlace(place);
        addASPotUI.setUser(user);
    }

    private void setUpPermitSpinner() {

        addASPotUI.setUpPermitSpinner(addASPotUI.getPermitRequired(), this);
        addASPotUI.getPermitRequired().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                permitRequired = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpUI() {
        setUpTypeSpinner();
        setUploadImageListener();
        setUpPermitSpinner();
        if (edit) {
            spotNumber = edit_data.getSpotNumber();
            price = edit_data.getPrice();
            description = edit_data.getDescription();
            type = edit_data.getType();
            permitRequired = edit_data.getPermitRequired();
            photo = edit_data.getPhoto();
            if (edit_data.getPhoto() != null && edit_data.getPhoto().equals("") && edit_data.getPhoto().equals("null")) {
                Glide.with(this).load(edit_data.getPhoto()).into(addASPotUI.getSpotImg());
            }
            addASPotUI.getActionBar().setTitle("Edit Spot");
            addASPotUI.getAddress().setText(edit_data.getAddress());
            addASPotUI.getDescription().setText(edit_data.getDescription());
            addASPotUI.getPrice().setText(String.valueOf(edit_data.getPrice()));
            addASPotUI.getSpotNum().setText(edit_data.getSpotNumber());
            addASPotUI.getType().setSelection(getPositionType(edit_data.getType()));
            addASPotUI.getPermitRequired().setSelection(getPositionPermit(edit_data.getPermitRequired()));
        }else {
            addASPotUI.getActionBar().setTitle("Add a Spot");
            addASPotUI.getAddress().setText(place.getAddress());
            addASPotUI.getDescription().setText("");
            addASPotUI.getPrice().setText(String.valueOf(0.0));
            addASPotUI.getSpotNum().setText("");

        }
    }

    private int getPositionType(String type) {
        int po = 0;
        switch (type.trim()) {
            case "Daily":
                po = 1;
                break;
            case "Semester":
                po = 2;
                break;
            case "Monthly":
                po = 3;
                break;
            default:
                po = 0;
                break;
        }
        return po;
    }
    private int getPositionPermit(String permit) {
        int po = 0;
        switch (permit.trim()) {
            case "No":
                po = 1;
                break;
            default:
                po = 0;
                break;
        }
        return po;
    }

    private void setUpTypeSpinner() {

        addASPotUI.setUpTypeSpinner(addASPotUI.getType(), this);
        addASPotUI.getType().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static void startIntent(Context context, User user, Place place) {
        Intent intent = new Intent(context, AddASpotActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, User user, Place place, Spot spot) {
        Intent intent = new Intent(context, AddASpotActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_PLACE, place);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        intent.putExtra(Constant.INTENT_EXTRA_SPOT, spot);
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
