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
public class Vehicle implements Serializable {
    private String vin;
    private String brand;
    private String make;
    private String year;
    private String photo;
    private String plateNumber;
    private String color;


    public Vehicle(String vin, String brand, String make, String year, String color, String plateNumber){

        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.plateNumber = plateNumber;
        this.color = color;
        this.photo=null;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Vehicle(String vin, String brand, String make, String year, String color, String plateNumber, String photo){
        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.photo = photo;
        this.plateNumber = plateNumber;
        this.color = color;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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
        result.put("color", color);
        return result;
    }
    public String getVehicleFullName(){
        return this.brand + " " + this.make + " " + this.year;

    }
}
