package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import android.graphics.Bitmap;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

@IgnoreExtraProperties
public class Spot extends Place{
    private String type;
    private String description;
    private double price;
    private boolean permitRequired = false;
    private String spotNumber;
    private Bitmap photo;

    private String renting;

    private String nextAvailable;

    public Spot(String address, String type, String description, double price, boolean permitRequired, String spotNumber, String renting, String nextAvailable) {
        super(address);
        this.type = type;
        this.description = description;
        this.price = price;
        this.permitRequired = permitRequired;
        this.spotNumber = spotNumber;
        this.renting = renting;
        this.nextAvailable = nextAvailable;
        this.photo = null;
    }

    public Spot(String address, String type, String description, double price, boolean permitRequired, String spotNumber, Bitmap photo, String renting, String nextAvailable) {
        super(address);
        this.type = type;
        this.description = description;
        this.price = price;
        this.permitRequired = permitRequired;
        this.spotNumber = spotNumber;
        this.photo = photo;
        this.renting = renting;
        this.nextAvailable = nextAvailable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPermitRequired() {
        return permitRequired;
    }

    public void setPermitRequired(boolean permitRequired) {
        this.permitRequired = permitRequired;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getRenting() {
        return renting;
    }

    public void setRenting(String renting) {
        this.renting = renting;
    }

    public String getNextAvailable() {
        return nextAvailable;
    }

    public void setNextAvailable(String nextAvailable) {
        this.nextAvailable = nextAvailable;
    }
}
