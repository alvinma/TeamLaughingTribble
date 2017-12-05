package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 12/4/17.
 */

public class PostDetailActivityViewModel extends ActivityCommonViewModel {

    private TextView title;
    private TextView author;
    private TextView date;
    private ImageView spotImage;
    private TextView spotNum;
    private TextView type;
    private TextView price;
    private TextView permit;
    private TextView description;
    private Button postButton;
    private Button bookButton;
    private Button commentsButton;


    public PostDetailActivityViewModel(Context context) {
        super(context);
        title = (TextView) ((Activity) context).findViewById(R.id.title);
        author= (TextView) ((Activity) context).findViewById(R.id.author);
        date= (TextView) ((Activity) context).findViewById(R.id.date);
        spotImage = (ImageView) ((Activity) context).findViewById(R.id.spot_image);
        spotNum =  (TextView) ((Activity) context).findViewById(R.id.spot_number_text);
        type = (TextView) ((Activity) context).findViewById(R.id.type_text);
        price = (TextView) ((Activity) context).findViewById(R.id.price_text);
         permit = (TextView) ((Activity) context).findViewById(R.id.permit_required_text);
         description = (TextView) ((Activity) context).findViewById(R.id.description_text);
         postButton = (Button) ((Activity) context).findViewById(R.id.post_button);
         bookButton = (Button) ((Activity) context).findViewById(R.id.book_button);
        commentsButton = (Button) ((Activity) context).findViewById(R.id.comments_button);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getAuthor() {
        return author;
    }

    public void setAuthor(TextView author) {
        this.author = author;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public ImageView getSpotImage() {
        return spotImage;
    }

    public void setSpotImage(ImageView spotImage) {
        this.spotImage = spotImage;
    }

    public TextView getSpotNum() {
        return spotNum;
    }

    public void setSpotNum(TextView spotNum) {
        this.spotNum = spotNum;
    }

    public TextView getType() {
        return type;
    }

    public void setType(TextView type) {
        this.type = type;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getPermit() {
        return permit;
    }

    public void setPermit(TextView permit) {
        this.permit = permit;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public Button getPostButton() {
        return postButton;
    }

    public void setPostButton(Button postButton) {
        this.postButton = postButton;
    }

    public Button getBookButton() {
        return bookButton;
    }

    public void setBookButton(Button bookButton) {
        this.bookButton = bookButton;
    }

    public Button getCommentsButton(){ return commentsButton;}

    public void setCommentsButton(Button commentsButton){ this.commentsButton = commentsButton;}
}
