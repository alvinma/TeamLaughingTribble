package edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.controllers.PostDetailActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Owner;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;
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

    public HomePostListAdapter(ArrayList<Post> posts, User user) {
        this.posts = posts;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public homePostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post_list_item, parent, false);
        homePostListUI = new homePostListItemViewHolder(mView);
        return homePostListUI;
    }

    @Override
    public void onBindViewHolder(homePostListItemViewHolder holder, int position) {
        final Post currentPost = posts.get(position);

        homePostListUI.getDatePosted().setText(currentPost.getDatePosted());
        homePostListUI.getPostTitle().setText(currentPost.getTitle());
        if(currentPost.getOwner() == null){
            currentPost.setOwner(new Owner());
        }
        homePostListUI.getPostOwner().setText(currentPost.getOwner().getFullName());

        if(currentPost.getSpot() == null){
            currentPost.setSpot(new Spot());
        }
        homePostListUI.getPostPrice().setText(String.valueOf("$"+currentPost.getSpot().getPrice()));
        homePostListUI.getPostTitle().setText(String.valueOf(currentPost.getTotalGrade()));
        homePostListUI.getPostDescription().setText(currentPost.getSpot().getDescription());
        homePostListUI.getTotalRate().setText(String.valueOf(currentPost.getTotalGrade()));

        if (currentPost.getSpot().getPhoto() != null) {
            Glide.with(homePostListUI.getContext()).load(currentPost.getSpot().getPhoto()).into(homePostListUI.getPostImage());
        } else {
            homePostListUI.getPostImage().setImageResource(R.drawable.not_available);
        }

        homePostListUI.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
                intent.putExtra(Constant.DEBUGGING, true);
                intent.putExtra(Constant.INTENT_EXTRA_POST, currentPost);
                intent.putExtra(Constant.INTENT_EXTRA_USER, user);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
