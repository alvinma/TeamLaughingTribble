package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

@IgnoreExtraProperties
public class Spot extends Place implements Serializable {
    private String type;
    private String description;
    private double price;
    private String permitRequired = "false";
    private String spotNumber;
    private String photo;
    private String renting;
    private String nextAvailable;

    //Sample Photos:
    //"https://previews.123rf.com/images/mack2happy/mack2happy1106/mack2happy110600045/9809346-parking-lot-Stock-Photo.jpg"
    //"https://static.pexels.com/photos/2996/parking-parking-lot-underground-garage.jpg"

    public Spot(){}

    public Spot(String address, String type, String description, double price, String permitRequired, String spotNumber, String renting, String nextAvailable) {
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

    public Spot(String address, String type, String description, double price, String permitRequired, String spotNumber, String renting, String nextAvailable, String photo) {
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

    public String isPermitRequired() {
        return permitRequired;
    }

    public void setPermit(String permitRequired) {
        this.permitRequired = permitRequired;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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

    public String getPermit() {
        return permitRequired;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("type", type);
        result.put("description", description);
        result.put("price", price);
        result.put("permitRequired", permitRequired);
        result.put("spotNumber", spotNumber);
        result.put("photo", photo);
        result.put("renting", renting);
        result.put("nextAvailable", nextAvailable);

        return result;
    }
}
