package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class Renter extends User {

    private ArrayList<Spot> spots;

    // Default Constructor
    public Renter() {}

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
}
