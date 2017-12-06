package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.AddPostActivityViewModel;

public class AddPostActivity extends BaseActivity {

    private AddPostActivityViewModel addPostActivityUI;
    private User user;

    private String title = "";

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, AddPostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        init();
    }

    private void init() {
        getDataFromIntent();
        setupUI();
    }
  
    private void getDataFromIntent() {
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
    }

    private void setupUI() {
        addPostActivityUI = new AddPostActivityViewModel(this);
        addPostActivityUI.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addPostActivityUI.getNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = addPostActivityUI.getTitle().getText().toString().trim();
                MyPlacesActivity.startIntent(getBaseContext(), user, title, true);
            }
        });
    }
    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, AddPostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
