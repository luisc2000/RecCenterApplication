package com.example.feature1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class Feature1BlackBoxTests {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testLogin() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(click());
        Thread.sleep(2600);
    }

    @Test
    public void testLoginNonExistent() throws InterruptedException { // logs in with user information that does not exists
        String username = "fake@usc.edu", password = "fake";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void testMap() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(click());
        Thread.sleep(6000);

        onView(ViewMatchers.withId(R.id.map)).perform(ViewActions.swipeLeft());
        Thread.sleep(3000);
        onView(ViewMatchers.withId(R.id.map)).perform(ViewActions.swipeRight());
        Thread.sleep(1400);

        onView(ViewMatchers.withId(R.id.map)).perform(ViewActions.swipeRight());
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.map)).perform(ViewActions.swipeLeft());
        Thread.sleep(2000);
    }
    @Test
    public void testNewUser() { // this test creates a new user
        onView(withId(R.id.tvRegister)).perform(ViewActions.click());
        String name = "Your Name Here", email = "verynewemail@usc.edu", password = "RandomPassword", id = "123456", URL = "url.com";
        onView(ViewMatchers.withId(R.id.etFullName)).perform(ViewActions.typeText(name));
        onView(ViewMatchers.withId(R.id.etRegName)).perform(ViewActions.typeText(email));
        onView(ViewMatchers.withId(R.id.etStudentID)).perform(ViewActions.typeText(id));
        onView(ViewMatchers.withId(R.id.etRegPassword)).perform(ViewActions.typeText(password));
        onView(ViewMatchers.withId(R.id.etPhotoURL)).perform(ViewActions.typeText(URL));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(ViewActions.click());
    }

    @Test
    public void testRecCenters() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(click());
        Thread.sleep(6000);
        onView(withId(R.id.xmlLyonButton)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.backBtn_feature2)).perform(click());

        onView(withId(R.id.xmlCromwellButton)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.backBtn_feature2)).perform(click());

        onView(withId(R.id.xmlUytengsuButton)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.backBtn_feature2)).perform(click());

        onView(withId(R.id.xmlVillageButton)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.backBtn_feature2)).perform(click());
    }
}