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

    public Place(){}

    public Place(String address) {
        this.address = address;
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
        return result;
    }
}