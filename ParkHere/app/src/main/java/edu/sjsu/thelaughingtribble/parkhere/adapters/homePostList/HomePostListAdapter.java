package edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class HomePostListAdapter extends RecyclerView.Adapter<homePostListItemViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>();
    private homePostListItemViewHolder homePostListUI;
    private User user;


    public HomePostListAdapter(ArrayList<Post> posts, User user) {
        this.posts = posts;
        this.user = user;

    }

    @Override
    public homePostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post_list_item, parent, false);
        homePostListUI = new homePostListItemViewHolder(mView);
        return homePostListUI;
    }

    @Override
    public void onBindViewHolder(homePostListItemViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        Log.i("currentPost title",  currentPost.getTitle());
        homePostListUI.setUser(user);
        homePostListUI.setPost(currentPost);
        homePostListUI.getDatePosted().setText(currentPost.getDatePosted());
        homePostListUI.getPostTitle().setText(currentPost.getTitle());
        homePostListUI.getPostOwner().setText(currentPost.getOwner().getUserID());
        homePostListUI.getPostPrice().setText(String.valueOf("$ "+currentPost.getSpot().getPrice()));
        homePostListUI.getPostDescription().setText(currentPost.getSpot().getDescription());
        homePostListUI.getTotalRate().setText(String.valueOf(currentPost.getTotalGrade()));

        if (currentPost.getSpot().getPhoto() != null) {
            Glide.with(homePostListUI.getContext()).load(currentPost.getSpot().getPhoto()).into(homePostListUI.getPostImage());
        } else {
            homePostListUI.getPostImage().setImageResource(R.drawable.not_available);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
