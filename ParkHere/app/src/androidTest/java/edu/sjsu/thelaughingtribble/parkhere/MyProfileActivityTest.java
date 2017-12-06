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
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Constant;
import edu.sjsu.thelaughingtribble.parkhere.controllers.LoginActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyProfileActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyProfileActivityTest {


    @Rule
    public ActivityTestRule<MyProfileActivity> myProfileActivityActivityTestRule = new ActivityTestRule<>(
            MyProfileActivity.class);


    @Test
    public void switchToEditMode(){
        onView(withId(R.id.edit)).perform(click());
        editMode();

    }


    private void editMode(){
        onView(withId(R.id.edit)).check(matches(not(isDisplayed())));
        onView(withId(R.id.done)).check(matches(isDisplayed()));

        onView(withId(R.id.full_name_edit_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.full_name_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));


        onView(withId(R.id.phone_edit_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.phone_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }
    @Test
    public void switchToViewMode(){
        String name = "John Doe";
        String phone = "408-123-4567";

        onView(withId(R.id.edit)).perform(click());
        onView(withId(R.id.full_name_edit_text)).perform(click()).perform(typeText(name));
        onView(withId(R.id.phone_edit_text)).perform(click()).perform(typeText(phone));
        closeSoftKeyboard();
        onView(withId(R.id.done)).perform(click());
        viewMode();

    }

    private void viewMode(){
        onView(withId(R.id.done)).check(matches(not(isDisplayed())));
        onView(withId(R.id.edit)).check(matches(isDisplayed()));

        onView(withId(R.id.full_name_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.full_name_edit_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.phone_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.phone_edit_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }

    @Test
    public void EditTextEmptyTest(){
        switchToEditMode();
        String fullName = "";
        String phone = "";

        onView(withId(R.id.full_name_edit_text)).perform(click()).perform(typeText(fullName));
        onView(withId(R.id.phone_edit_text)).perform(click()).perform(typeText(phone));
        closeSoftKeyboard();
        onView(withId(R.id.done)).perform(click());

        onView(withId(R.id.full_name_edit_text)).check(matches(hasErrorText(Constant.REQUIRE_TEXT)));
        onView(withId(R.id.phone_edit_text)).check(matches(hasErrorText(Constant.PHONE_FORMAT_TEXT)));
    }

    @Test
    public void PhoneEditTextInValidTest(){
        switchToEditMode();
        String fullName = "John Doe";
        String phone = "4082314586";

        onView(withId(R.id.full_name_edit_text)).perform(click()).perform(typeText(fullName));
        onView(withId(R.id.phone_edit_text)).perform(click()).perform(typeText(phone));
        closeSoftKeyboard();
        onView(withId(R.id.done)).perform(click());

        onView(withId(R.id.phone_edit_text)).check(matches(hasErrorText(Constant.PHONE_FORMAT_TEXT)));
    }

}