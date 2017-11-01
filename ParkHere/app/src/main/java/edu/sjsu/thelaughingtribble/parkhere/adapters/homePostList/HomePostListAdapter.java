package edu.sjsu.thelaughingtribble.parkhere.adapters.homePostList;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public class HomePostListAdapter extends RecyclerView.Adapter<homePostListItemViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>();
    private homePostListItemViewHolder homePostListUI;

    public HomePostListAdapter(ArrayList<Post> posts){
        this.posts = posts;
        Log.i("Adapter: ", posts.get(0).getTitle());
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

        homePostListUI.getDatePosted().setText(currentPost.getDatePosted());
        homePostListUI.getPostTitle().setText(currentPost.getTitle());
        homePostListUI.getPostOwner().setText(currentPost.getOwner().getFullName());
        homePostListUI.getPostImage().setImageBitmap(currentPost.getSpot().getPhoto());
        homePostListUI.getPostPrice().setText(String.valueOf(currentPost.getSpot().getPrice()));
        homePostListUI.getPostDescription().setText(currentPost.getSpot().getDescription());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}