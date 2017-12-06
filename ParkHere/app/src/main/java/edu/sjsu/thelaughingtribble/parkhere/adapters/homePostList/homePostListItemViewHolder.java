package edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyProfileActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class homePostListItemViewHolder extends RecyclerView.ViewHolder{

    private TextView postTitle;
    private ImageView postImage;
    private TextView postOwner;
    private TextView datePosted;
    private TextView postPrice;
    private TextView postDescription;
    private Context context;
    private TextView totalRate;
    /*

        view is the inflated xml layout
         */
    public homePostListItemViewHolder(View homePostListItem) {
        super(homePostListItem);
        this.context = homePostListItem.getContext();
        this.postTitle = (TextView) homePostListItem.findViewById(R.id.post_title);
        this.postImage = (ImageView) homePostListItem.findViewById(R.id.post_image);
        this.postOwner = (TextView) homePostListItem.findViewById(R.id.post_owner);
        this.datePosted = (TextView) homePostListItem.findViewById(R.id.post_date_posted);
        this.postPrice = (TextView) homePostListItem.findViewById(R.id.post_price);
        this.postDescription = (TextView) homePostListItem.findViewById(R.id.post_description);
        this.totalRate = (TextView) homePostListItem.findViewById(R.id.total_rate);
    }

    public TextView getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(TextView totalRate) {
        this.totalRate = totalRate;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(TextView postTitle) {
        this.postTitle = postTitle;
    }

    public ImageView getPostImage() {
        return postImage;
    }

    public void setPostImage(ImageView postImage) {
        this.postImage = postImage;
    }

    public TextView getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(TextView postOwner) {
        this.postOwner = postOwner;
    }

    public TextView getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(TextView datePosted) {
        this.datePosted = datePosted;
    }

    public TextView getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(TextView postPrice) {
        this.postPrice = postPrice;
    }

    public TextView getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(TextView postDescription) {
        this.postDescription = postDescription;
    }
}
