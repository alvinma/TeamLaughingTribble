package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

@IgnoreExtraProperties
public class CommentAndRating implements Serializable{
    private Spot spot;
    private double grade;
    private String renter;
    private String comment;
    private String date;

    public CommentAndRating(){}

    public CommentAndRating(Spot spot, double grade, String renter, String comment, String date) {
        this.spot = spot;
        this.grade = grade;
        this.renter = renter;
        this.comment = comment;
        this.date = date;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("spot", spot);
        result.put("grade", grade);
        result.put("renter", renter);
        result.put("comment", comment);
        result.put("date", date);

        return result;
    }
}
