package edu.sjsu.thelaughingtribble.parkhere;


public class ParkingPostObject {

    private String OwnerName;
    private String AddressLocation;
    private String Description;

    // Object Constructors

    ParkingPostObject(){
        OwnerName = "Not Given";
        AddressLocation = "Not Given";
        Description = "Not Given";
    }

    // Object Setters

    public void setOwnerName(String name){
        this.OwnerName = name;
    }

    public void setAddressLocation(String address){
        this.AddressLocation = address;
    }

    public void setDescription(String description){
        this.Description = description;
    }

    // Object Getters

    public String getOwnerName(){
        return OwnerName;
    }

    public String getAddressLocation(){
        return AddressLocation;
    }

    public String getDescription(){
        return Description;
    }

}
