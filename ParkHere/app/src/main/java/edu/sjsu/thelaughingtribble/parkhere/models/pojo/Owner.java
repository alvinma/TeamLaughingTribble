package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */
@IgnoreExtraProperties
public class Owner  extends User implements Serializable {

    public Owner(){
        // Default constructor required for calls to DataSnapshot.getValue(Owner.class)
    }

    public Owner(String userID, String firstName, String lastName, String email, String cellphone) {
        super(userID, firstName, lastName, email, cellphone);
    }

    /*public Owner(String userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
    }*/

    public Owner(String userID, String email) {
        super(userID, email);
    }

    public Owner(String uid, String userID, String email, String fullName) {
        super(uid, userID, email, fullName);
    }


    @Exclude
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> result = super.toMap();
        return result;
    }
}