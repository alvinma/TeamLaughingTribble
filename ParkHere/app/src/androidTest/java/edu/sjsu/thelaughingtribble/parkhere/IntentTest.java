package edu.sjsu.thelaughingtribble.parkhere;

/**
 * Created by jennifernghinguyen on 11/7/17.
 */

import android.os.SystemClock;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.sjsu.thelaughingtribble.parkhere.controllers.MainActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyPlacesActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyProfileActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MySpotsActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.MyVehiclesActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.Navigation;
import edu.sjsu.thelaughingtribble.parkhere.controllers.NotificationActivity;
import edu.sjsu.thelaughingtribble.parkhere.controllers.ProfileActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityTestRule = new IntentsTestRule<>(MainActivity.class);



    @Test
    public void homeMenuIntent() {

        onView(withId(R.id.home_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(MainActivity.class.getName()));

    }


    @Test
    public void notificationIntent() {
        onView(withId(R.id.notification_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(NotificationActivity.class.getName()));

    }

    @Test
    public void profileIntent() {
        onView(withId(R.id.profile_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(ProfileActivity.class.getName()));

    }

    @Test
    public void myProfileIntent() {
        onView(withId(R.id.profile_layout)).perform(click());
        onView(withId(R.id.myprofile_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(MyProfileActivity.class.getName()));

    }

    @Test
    public void myVehicleIntent() {
        onView(withId(R.id.profile_layout)).perform(click());
        onView(withId(R.id.myvehicle_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(MyVehiclesActivity.class.getName()));

    }
    @Test
    public void myPlaceIntent() {
        onView(withId(R.id.profile_layout)).perform(click());
        onView(withId(R.id.myplaces_layout)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(MyPlacesActivity.class.getName()));

    }

    @Test
    public void mySpotIntent() {
        onView(withId(R.id.profile_layout)).perform(click());
        onView(withId(R.id.myplaces_layout)).perform(click());
        onView(withId(R.id.address_text)).perform(click());
        SystemClock.sleep(1000);
        intended(hasComponent(MySpotsActivity.class.getName()));

    }

}
