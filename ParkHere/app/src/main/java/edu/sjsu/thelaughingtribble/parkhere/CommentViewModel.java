package edu.sjsu.thelaughingtribble.parkhere;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.ActivityCommonViewModel;

/**
 * Created by Alvin on 12/4/2017.
 */

public class CommentViewModel extends ActivityCommonViewModel {
    private RecyclerView commentList;


    public CommentViewModel(Context context) {
        super(context);
        this.commentList = (RecyclerView) ((Activity) context).findViewById(R.id.commentList);
    }

    public RecyclerView getCommentList() {
        return commentList;
    }

    public void setCommentList(RecyclerView homePostList) {
        this.commentList = homePostList;
    }
}

