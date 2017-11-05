package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

@IgnoreExtraProperties
public class Vehicle {
    private String vin;
    private String brand;
    private String make;
    private String year;
    private Bitmap photo;
    private String plateNumber;

    public Vehicle(){}

    public Vehicle(String vin, String brand, String make, String year, String plateNumber){
        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.plateNumber = plateNumber;
    }

    public Vehicle(String vin, String brand, String make, String year, String plateNumber, Bitmap photo){
        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.photo = photo;
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVin() {
        return vin;

    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("vin", vin);
        result.put("brand", brand);
        result.put("make", make);
        result.put("year", year);
        result.put("photo", photo);
        result.put("plateNumber", plateNumber);

        return result;
    }
}
