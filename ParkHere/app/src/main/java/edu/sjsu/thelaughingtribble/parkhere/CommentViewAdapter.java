package edu.sjsu.thelaughingtribble.parkhere;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.CommentAndRating;

public class CommentViewAdapter extends RecyclerView.Adapter<commentViewListItemViewHolder> {
    private ArrayList<CommentAndRating> commentAndRatings = new ArrayList<>();
    private commentViewListItemViewHolder commentUI;

    public CommentViewAdapter(ArrayList<CommentAndRating> commentAndRatings) {
        this.commentAndRatings = commentAndRatings;
    }

    @Override
    public commentViewListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view_adapter_item, parent, false);
        commentUI = new commentViewListItemViewHolder(mView);
        return commentUI;
    }

    @Override
    public void onBindViewHolder(commentViewListItemViewHolder holder, int position) {
        CommentAndRating currentPost = commentAndRatings.get(position);

        commentUI.getDatePosted().setText(currentPost.getDate());
        //commentUI.getCommentTitle().setText(String.valueOf(currentPost.getTotalGrade()));
        commentUI.getRenterName().setText(currentPost.getRenter());
        commentUI.getComment().setText(currentPost.getComment());
    }

    @Override
    public int getItemCount() {
        return commentAndRatings.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }
}
