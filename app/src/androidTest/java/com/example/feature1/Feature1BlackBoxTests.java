package com.example.feature1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewAction;
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
    public void testLogin() {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
    }

//    @Test
//    public void testNewUser() {
//        String username = "Test@usc.edu", password = "JonBell";
//        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
//        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
//        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
//        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
//    }
}