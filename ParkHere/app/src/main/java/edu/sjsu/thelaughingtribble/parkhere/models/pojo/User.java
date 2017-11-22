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
public class User implements Serializable {
    private String uid;
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;
    private ArrayList<Vehicle> vehicles;

    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userID, String firstName, String lastName, String email, String cellphone) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
        this.vehicles = new ArrayList<>();
    }

    public User(String userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.vehicles = new ArrayList<>();
    }

    public User(String userID, String firstName, String lastName, String email, String cellphone, ArrayList<Vehicle> vehicles) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
        this.vehicles = vehicles;
    }


    public User(String userID, String firstName, String lastName, String email, ArrayList<Vehicle> vehicles) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addAVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }
    public User(String userID, String email) {
        this.userID = userID;
        this.email = email;
    }

    public User(String uid, String userID, String email) {
        this.uid = uid;
        this.userID = userID;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", userID);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("email", email);
        result.put("cellphone", cellphone);
        result.put("vehicles", vehicles);

        return result;
    }
}
