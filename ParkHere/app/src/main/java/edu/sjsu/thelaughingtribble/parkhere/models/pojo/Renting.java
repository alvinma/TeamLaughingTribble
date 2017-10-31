package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class Renting {
    private Spot spot;
    private Renter renter;
    private Owner owner;
    private String startDate;
    private String endDate;

    public Renting(Spot spot, Renter renter, Owner owner, String startDate, String endDate) {
        this.spot = spot;
        this.renter = renter;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
