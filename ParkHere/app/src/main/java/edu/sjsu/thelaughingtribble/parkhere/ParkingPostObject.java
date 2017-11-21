package edu.sjsu.thelaughingtribble.parkhere;


public class ParkingPostObject {

    private Integer ObjectID;
    private String OwnerName;
    private String AddressLocation;
    private String Description;

    // Object Constructors

    ParkingPostObject() {
        ObjectID = -1;
        OwnerName = "Not Given";
        AddressLocation = "Not Given";
        Description = "Not Given";
    }

    // Object Setters

    public void setParkingPost(Integer id, String name, String location, String desc) {
        this.ObjectID = id;
        this.OwnerName = name;
        this.AddressLocation = location;
        this.Description = desc;
    }

    public void setObjectID(Integer id) {
        this.ObjectID = id;
    }

    public void setOwnerName(String name) {
        this.OwnerName = name;
    }

    public void setAddressLocation(String address) {
        this.AddressLocation = address;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    // Object Getters

    public Integer getObjectID() {
        return ObjectID;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public String getAddressLocation() {
        return AddressLocation;
    }

    public String getDescription() {
        return Description;
    }

}
