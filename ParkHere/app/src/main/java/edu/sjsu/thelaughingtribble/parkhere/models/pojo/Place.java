package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */
@IgnoreExtraProperties
public class Place implements Serializable {
    private String address;
    private String firebaseKey;

    // Default Constructor
    public Place() {}

    public Place(String address) {
        this.firebaseKey = firebaseKey;
        this.address = address;
    }
    public Place(String firebaseKey, String address) {
        this.firebaseKey = firebaseKey;
        this.address = address;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("firebaseKey", firebaseKey);
        return result;
    }
}