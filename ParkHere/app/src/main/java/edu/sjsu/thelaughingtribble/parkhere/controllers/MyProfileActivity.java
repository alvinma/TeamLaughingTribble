package edu.sjsu.thelaughingtribble.parkhere.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;
import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;
import edu.sjsu.thelaughingtribble.parkhere.models.viewModels.MyProfileActivityViewModel;

public class MyProfileActivity extends AppCompatActivity {

    MyProfileActivityViewModel myProfileActivityUIComponents;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        user = (User) getIntent().getSerializableExtra(Constant.INTENT_EXTRA_USER);
        myProfileActivityUIComponents = new MyProfileActivityViewModel(this);
        myProfileActivityUIComponents.setUser(user);

        if (myProfileActivityUIComponents.getActionBar() != null) {
            myProfileActivityUIComponents.getActionBar().setTitle("My Profile");
            myProfileActivityUIComponents.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editAction(myProfileActivityUIComponents.getEditButton());

        doneAction(myProfileActivityUIComponents.getDoneButton());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editMode(boolean edit) {
        if (edit) {
            myProfileActivityUIComponents.getFullName().setVisibility(View.GONE);
            myProfileActivityUIComponents.getPhone().setVisibility(View.GONE);

            myProfileActivityUIComponents.getFullNameEdit().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getPhoneEdit().setVisibility(View.VISIBLE);

            myProfileActivityUIComponents.getEditButton().setVisibility(View.GONE);
            myProfileActivityUIComponents.getDoneButton().setVisibility(View.VISIBLE);
        } else {
            myProfileActivityUIComponents.getFullName().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getPhone().setVisibility(View.VISIBLE);

            myProfileActivityUIComponents.getFullNameEdit().setVisibility(View.GONE);
            myProfileActivityUIComponents.getPhoneEdit().setVisibility(View.GONE);

            myProfileActivityUIComponents.getEditButton().setVisibility(View.VISIBLE);
            myProfileActivityUIComponents.getDoneButton().setVisibility(View.GONE);
        }
    }

    private void doneAction(FloatingActionButton done) {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = myProfileActivityUIComponents.getFullNameEdit().getText().toString().trim();
                String phone = myProfileActivityUIComponents.getPhoneEdit().getText().toString().trim();

                if (myProfileActivityUIComponents.isEmptyFields(myProfileActivityUIComponents.getFullNameEdit())) {
                    myProfileActivityUIComponents.setError(myProfileActivityUIComponents.getFullNameEdit(), Constant.REQUIRE_TEXT);
                }


                if (!Utilities.phoneMatcher(phone)) {
                    myProfileActivityUIComponents.setError(myProfileActivityUIComponents.getPhoneEdit(), Constant.PHONE_FORMAT_TEXT);
                }


                if (!(TextUtils.isEmpty(fullName))  && Utilities.phoneMatcher(phone)) {
                    editMode(false);
                }


            }
        });
    }


    private void editAction(FloatingActionButton edit) {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMode(true);
            }
        });
    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MyProfileActivity.class);
        Log.i("myprofile startIntent", user.getUid() + " " + user.getEmail());
        intent.putExtra(Constant.INTENT_EXTRA_USER, user);
        context.startActivity(intent);
    }
}
