/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.sjsu.thelaughingtribble.parkhere;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.sjsu.thelaughingtribble.parkhere.controllers.LoginActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Before
    public void signoutIfLoggedIn() {
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            Intent intent = new Intent(getTargetContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getTargetContext().startActivity(intent);
        }
    }


    @Test
    public void falseloginWithANonExistedEmail() {

        String username = "notvalid@gmail.com";
        String password = "12345";

        onView(withId(R.id.field_email)).perform(click()).perform(typeText(username));
        onView(withId(R.id.field_password)).perform(click()).perform(typeText(password));
        onView(withId(R.id.button_sign_in)).perform(click());
        SystemClock.sleep(500);
        isToastMessageDisplayed("Sign In Failed");

    }

    @Test
    public void falseloginWithWrongPassWord() {


        //test data
        String username = "laughingtribble@gmail.com";
        String password = "1234";

        onView(withId(R.id.field_email)).perform(click()).perform(typeText(username));
        onView(withId(R.id.field_password)).perform(click()).perform(typeText(password));
        onView(withId(R.id.button_sign_in)).perform(click());

        SystemClock.sleep(1000);

        isToastMessageDisplayed("Sign In Failed");

    }

    @Test
    public void correctLogin() {

        //test data
        String username = "laughingtribble@gmail.com";
        String password = "laughingtribbleteam";

        onView(withId(R.id.field_email)).perform(click()).perform(typeText(username));
        onView(withId(R.id.field_password)).perform(click()).perform(typeText(password));
        onView(withId(R.id.button_sign_in)).perform(click());

        isToastMessageDisplayed("Sign In success");

    }

    @Test
    public void signOutTest(){
        correctLogin(); // login with correct user

        FirebaseAuth result = LoginActivity.signOut(mAuth);

        assertTrue(result==null);

    }

    private void isToastMessageDisplayed(String textId) {
        onView(withText(textId)).inRoot(withDecorView(not(is(loginActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}