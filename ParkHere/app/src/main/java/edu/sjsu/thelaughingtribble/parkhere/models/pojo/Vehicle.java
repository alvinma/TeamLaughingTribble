package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import android.graphics.Bitmap;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class Vehicle {
    private String vin;
    private String brand;
    private String make;
    private String year;
    private Bitmap photo;

    public Vehicle(String vin, String brand, String make, String year){
        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public Vehicle(String vin, String brand, String make, String year, Bitmap photo){
        this.vin = vin;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.photo = photo;
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
}