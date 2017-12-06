package edu.sjsu.thelaughingtribble.parkhere;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alvin on 12/1/2017.
 */

public class commentViewListItemViewHolder extends RecyclerView.ViewHolder {

    private TextView commentTitle;
    private TextView renterName;
    private TextView datePosted;
    private TextView comment;
    private Context context;


    /*

        view is the inflated xml layout
         */
    public commentViewListItemViewHolder(View commentItem) {
        super(commentItem);
        this.context = commentItem.getContext();
        this.commentTitle = (TextView) commentItem.findViewById(R.id.title);
        this.renterName = (TextView) commentItem.findViewById(R.id.full_name);
        this.datePosted = (TextView) commentItem.findViewById(R.id.date_posted);
        this.comment = (TextView) commentItem.findViewById(R.id.post_description);
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(TextView commentTitle) {
        this.commentTitle = commentTitle;
    }

    public TextView getRenterName() {
        return renterName;
    }

    public void setRenterName(TextView renterName) {
        this.renterName = renterName;
    }

    public TextView getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(TextView datePosted) {
        this.datePosted = datePosted;
    }

    public TextView getComment() {
        return comment;
    }

    public void setComment(TextView comment) {
        this.comment = comment;
    }
}
