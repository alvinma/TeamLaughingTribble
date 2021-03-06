package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */


@IgnoreExtraProperties
public class Post implements Serializable {

    private String placeID;
    private String spotId;
    private String authorId;
    private Spot spot;
    private Owner owner;
    private ArrayList<CommentAndRating> commentAndRatings;
    private double totalGrade;
    private String datePosted;
    private String title;
    private String dateExpired;

    public Post() {

    }
    //new post
    public Post(String title, Spot spot, Owner owner, String datePosted, double totalGrade) {
        this.title = title;
        this.spot = spot;
        this.owner = owner;
        this.datePosted = datePosted;
        this.totalGrade = totalGrade;
    }
    public Post(String placeID, String spotId, String authorId, String title) {
        this.placeID = placeID;
        this.spotId = spotId;
        this.authorId = authorId;
        this.title = title;
        this.datePosted = Utilities.getTodayDate();
        this.dateExpired = Utilities.setDateExpired(7);
        this.totalGrade = 5.0;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    //new post
    public Post(String title, Spot spot, Owner owner, String datePosted) {
        this.title = title;
        this.spot = spot;
        this.owner = owner;
        this.datePosted = datePosted;
        commentAndRatings = new ArrayList<>();
        totalGrade = Constant.DEFAULT_RATING_GRADE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post(String title, Spot spot, Owner owner, String datePosted, ArrayList<CommentAndRating> commentAndRatings) {
        this.title = title;
        this.spot = spot;
        this.owner = owner;
        this.datePosted = datePosted;
        this.commentAndRatings = commentAndRatings;
        this.totalGrade = getAveragetotalGrade(this.commentAndRatings);
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }


    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }


    public ArrayList<CommentAndRating> getCommentAndRatings() {
        return commentAndRatings;
    }

    public void setCommentAndRatings(ArrayList<CommentAndRating> commentAndRatings) {
        this.commentAndRatings = commentAndRatings;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public double getAveragetotalGrade(ArrayList<CommentAndRating> commentAndRatings) {
        double totalGrade = 0;
        int size = commentAndRatings.size();
        for (CommentAndRating c : commentAndRatings) {
            totalGrade += c.getGrade();
        }

        return totalGrade / (double) size;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("dateExpired", dateExpired);
        result.put("datePosted", datePosted);
        result.put("ownerId", authorId);
        result.put("placeID", placeID);
        result.put("spotId", spotId);
        result.put("title", title);
        result.put("totalGrade", totalGrade);
        return result;
    }
}
