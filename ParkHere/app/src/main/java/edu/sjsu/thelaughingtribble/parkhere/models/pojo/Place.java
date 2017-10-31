package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class Place {
    private String address;

    public Place(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
