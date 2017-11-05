package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */


@IgnoreExtraProperties
public class Post {
    private Spot spot;
    private Owner owner;
    private ArrayList<CommentAndRating> commentAndRatings;
    private double totalGrade;
    private String datePosted;
    private String title;

    // Default Constructor
    public Post() {};

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

    public double getAveragetotalGrade(ArrayList<CommentAndRating> commentAndRatings){
        double totalGrade = 0;
        int size = commentAndRatings.size();
        for(CommentAndRating c: commentAndRatings){
            totalGrade += c.getGrade();
        }

        return totalGrade/ (double) size;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("spot", spot);
        result.put("owner", owner);
        result.put("totalGrade", totalGrade);
        result.put("commentAndRatings", commentAndRatings);

        return result;
    }
}
