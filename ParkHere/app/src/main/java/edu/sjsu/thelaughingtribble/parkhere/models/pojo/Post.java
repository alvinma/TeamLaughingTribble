package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class Post {

    private Spot spot;
    private Owner owner;
    private ArrayList<CommentAndRating> commentAndRatings;
    private double totalGrade;

    //new post
    public Post(Spot spot, Owner owner) {
        this.spot = spot;
        this.owner = owner;
        commentAndRatings = new ArrayList<>();
        totalGrade = Constant.DEFAULT_RATING_GRADE;
    }

    public Post(Spot spot, Owner owner, ArrayList<CommentAndRating> commentAndRatings) {
        this.spot = spot;
        this.owner = owner;
        this.commentAndRatings = commentAndRatings;
        this.totalGrade = getAveragetotalGrade(this.commentAndRatings);
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
}
