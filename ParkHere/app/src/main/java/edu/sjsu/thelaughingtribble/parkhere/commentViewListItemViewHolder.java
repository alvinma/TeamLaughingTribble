package edu.sjsu.thelaughingtribble.parkhere;


import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by Alvin on 12/1/2017.
 */

public class commentViewListItemViewHolder extends RecyclerView.ViewHolder {

    private TextView postTitle;
    private TextView postOwner;
    private TextView datePosted;
    private TextView postDescription;
    private Context context;


    /*

        view is the inflated xml layout
         */
    public commentViewListItemViewHolder(View commentItem) {
        super(commentItem);
        this.context = commentItem.getContext();
        this.postTitle = (TextView) commentItem.findViewById(R.id.title);
        this.postOwner = (TextView) commentItem.findViewById(R.id.full_name);
        this.datePosted = (TextView) commentItem.findViewById(R.id.date_posted);
        this.postDescription = (TextView) commentItem.findViewById(R.id.post_description);
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

    public TextView getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(TextView postDescription) {
        this.postDescription = postDescription;
    }
}
