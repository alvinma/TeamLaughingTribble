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
import edu.sjsu.thelaughingtribble.parkhere.controllers.PostInformationActivity;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class HomePostListAdapter extends RecyclerView.Adapter<homePostListItemViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>();
    private homePostListItemViewHolder homePostListUI;

    public HomePostListAdapter(ArrayList<Post> posts) {
        this.posts = posts;

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
        homePostListUI.getPostOwner().setText(currentPost.getOwner().getFullName());
        homePostListUI.getPostPrice().setText(String.valueOf("$"+currentPost.getSpot().getPrice()));
        homePostListUI.getPostTitle().setText(String.valueOf(currentPost.getTotalGrade()));
        homePostListUI.getPostDescription().setText(currentPost.getSpot().getDescription());

        if (currentPost.getSpot().getPhoto() != null) {
            Glide.with(homePostListUI.getContext()).load(currentPost.getSpot().getPhoto()).into(homePostListUI.getPostImage());
        } else {
            homePostListUI.getPostImage().setImageResource(R.drawable.not_available);
        }

        homePostListUI.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "TESTING OUT THE NEW FEATURE", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Toast.makeText(view.getContext(), "Clicked on item", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), PostInformationActivity.class);
                intent.putExtra("Post.class", currentPost);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
