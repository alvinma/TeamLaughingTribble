package edu.sjsu.thelaughingtribble.parkhere;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.controllers.BaseActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Owner;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;

public class CreateParkingSpotListing extends BaseActivity {

    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText mTitleField;
    private EditText mBodyField;

    Button mSubmitButton;
    EditText mNameField;
    EditText mLocationField;
    EditText mDescriptionField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_parking_spot_listing);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mNameField = (EditText) findViewById(R.id.ownerName);
        mLocationField = (EditText) findViewById(R.id.spotLocation);
        mDescriptionField = (EditText) findViewById(R.id.description);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitPost();
            }
        });
    }

    private void submitPost() {
        final String name = mNameField.getText().toString();
        final String location = mLocationField.getText().toString();
        final String description = mDescriptionField.getText().toString();

//        final String title = mTitleField.getText().toString();
//        final String body = mBodyField.getText().toString();

        // Name is required
        if (TextUtils.isEmpty(name)) {
            mNameField.setError(REQUIRED);
            return;
        }

        // Location is required
        if (TextUtils.isEmpty(location)) {
            mLocationField.setError(REQUIRED);
            return;
        }

        //Description is required
        if (TextUtils.isEmpty(description)) {
            mDescriptionField.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Owner user = dataSnapshot.getValue(Owner.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(CreateParkingSpotListing.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user, location, description);
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        mNameField.setEnabled(enabled);
        mLocationField.setEnabled(enabled);
        mDescriptionField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String userId, Owner user, String location, String description) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        //Post post = new Post(userId, username, title, body);
        String type = "SUV";
        double price = 10.99;
        String permitRequired = "false";
        String spotNumber = "1";
        String renting = "Yes";
        String nextAvaliable = "11-02-17";
        String defaultTitle = "SUV Parking";
        Spot spot = new Spot(location, type, description, price, permitRequired, spotNumber, renting, nextAvaliable, "123","sa",  "example key");
        Post post = new Post(defaultTitle, spot, user, Utilities.getTodayDate());
        Post post = new Post(defaultTitle, "asd", "asda", Utilities.getTodayDate());
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/post/" + key, postValues);
        childUpdates.put("/user/posts" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
        Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
    }
    // [END write_fan_out]

}
