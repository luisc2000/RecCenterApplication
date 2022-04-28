package com.example.feature1;

import static androidx.test.espresso.Espresso.onView;
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
public class Feature3BlackBoxTests
{
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);
    @Test
    public void testReminders() throws InterruptedException {
        String username = "AutoTestR@usc.edu", password = "tester";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withText("Village Apr 12, 2022 1200-1400 capacity(3/5)")).check(matches(isDisplayed()));
        onView(withText("Uytengsu Apr 12, 2022 1200-1400 capacity(1/5)")).check(matches(isDisplayed()));
        onView(withText("Cromwell Apr 11, 2022 1200-1400 capacity(0/5)")).check(matches(isDisplayed()));
    }
    @Test
    public void testUpcomingAppts() throws InterruptedException
    {
        String username = "AutoTestU@usc.edu", password = "tester";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withText("Lyon|Apr 13, 2023|1200-1400")).check(matches(withId(R.id.view)));
        onView(withText("Village|Apr 11, 2023|1000-1200")).check(matches(withId(R.id.view3)));
        onView(withText("Cromwell|Apr 12, 2023|1200-1400")).check(matches(withId(R.id.view5)));
        onView(withText("Uytengsu|Apr 13, 2023|1000-1200")).check(matches(withId(R.id.view6)));
        Thread.sleep(1000);
        onView(withId(R.id.button15)).perform(ViewActions.click());
    }

    @Test
    public void testPreviousAppts() throws InterruptedException {
        String username = "AutoTestP@usc.edu", password = "tester";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withText("Cromwell|Mar 27, 2022|1200-1400")).check(matches(withId(R.id.view44)));
        onView(withText("Lyon|Mar 29, 2022|1400-1600")).check(matches(withId(R.id.view43)));
        onView(withText("Uytengsu|Apr 8, 2022|1400-1600")).check(matches(withId(R.id.view42)));
        onView(withText("Lyon|Apr 6, 2022|1000-1200")).check(matches(withId(R.id.view20)));
        onView(withText("Cromwell|Apr 11, 2023|1200-1400")).check(matches(withId(R.id.view41)));
    }
    @Test
    //Test Reminder
    public void testAllOneAccount() throws InterruptedException {
        String username = "AutoTest1@usc.edu", password = "tester";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withText("Lyon Apr 10, 2022 1200-1400 capacity(0/5)")).check(matches(withId(R.id.view10)));
        onView(withText("Cromwell Apr 11, 2022 1400-1600 capacity(2/5)")).check(matches(withId(R.id.view7)));
        onView(withText("Uytengsu Apr 12, 2022 1400-1600 capacity(2/5)")).check(matches(withId(R.id.view8)));

        onView(withText("Lyon|Apr 12, 2023|1400-1600")).check(matches(withId(R.id.view)));
        onView(withText("Uytengsu|Apr 11, 2023|1400-1600")).check(matches(withId(R.id.view3)));
        onView(withText("Village|Apr 10, 2023|1000-1200")).check(matches(withId(R.id.view5)));
        onView(withText("Lyon|Apr 10, 2023|1400-1600")).check(matches(withId(R.id.view6)));
        onView(withText("Uytengsu|Apr 12, 2023|1400-1600")).check(matches(withId(R.id.view13)));

        onView(withText("Lyon|Mar 27, 2022|1200-1400")).check(matches(withId(R.id.view44)));
        onView(withText("Cromwell|Mar 29, 2022|1400-1600")).check(matches(withId(R.id.view43)));
        onView(withText("Uytengsu|Apr 8, 2022|1400-1600")).check(matches(withId(R.id.view42)));
        onView(withText("Cromwell|Apr 6, 2022|1000-1200")).check(matches(withId(R.id.view20)));
        onView(withText("Lyon|Apr 11, 2023|1200-1400")).check(matches(withId(R.id.view41)));
    }

    @Test
    public void testRemainingButtons() throws InterruptedException {
        String username = "AutoTestU@usc.edu", password = "tester";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        onView(withId(R.id.button4)).perform(ViewActions.click());
        onView(withId(R.id.backSummary)).perform(ViewActions.click());
    }
}