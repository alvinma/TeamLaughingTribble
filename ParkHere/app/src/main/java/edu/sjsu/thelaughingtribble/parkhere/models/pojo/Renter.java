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
public class Renter extends User implements Serializable {

    private ArrayList<Spot> spots;

    public Renter(){}

    public Renter(String userID, String firstName, String lastName, String email, String cellphone) {
        super(userID, firstName, lastName, email, cellphone);
        spots = new ArrayList<>();
    }

    public Renter(String userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
        spots = new ArrayList<>();
    }

    public Renter(String userID, String email) {
        super(userID, email);
        spots = new ArrayList<>();
    }

    public ArrayList<Spot> getSpots() {
        return spots;
    }

    public void setSpots(ArrayList<Spot> spots) {
        this.spots = spots;
    }

    //add a single spot to a place
    public void addASpot(Spot spot) {
        spots.add(spot);
    }

    @Exclude
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> result = super.toMap();
        result.put("spots", spots);

        return result;
    }
}