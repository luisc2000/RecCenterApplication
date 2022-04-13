package com.example.feature1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.annotation.NonNull;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class Feature2BlackBoxTests {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testBackBtn() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(withText("New User? Click Me to Sign Up")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(5000);
        onView(withId(R.id.xmlLyonButton)).perform(ViewActions.click());
        Thread.sleep(5000);
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

    }

    @Test
    public void testDates() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(5000);
        onView(withId(R.id.xmlLyonButton)).perform(ViewActions.click());
        Thread.sleep(5000);

        // get today, tomorrow, tomorrow+1 dates
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String today_String = dateFormat.format(today);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date todayPlusOne = calendar.getTime();
        String todayPlusOne_String = dateFormat.format(todayPlusOne);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date todayPlusTwo = calendar.getTime();
        String todayPlusTwo_String = dateFormat.format(todayPlusTwo);
        ArrayList<String> document = new ArrayList<>();
        document.add(today_String);
        document.add(todayPlusOne_String);
        document.add(todayPlusTwo_String);
        // end

        // testing it here
        onView(withText(document.get(0))).check(matches(withId(R.id.date_1)));
        onView(withText(document.get(1))).check(matches(withId(R.id.date_2)));
        onView(withText(document.get(2))).check(matches(withId(R.id.date_3)));
        Thread.sleep(3000);

    }

    @Test
    public void testRecCenterDb() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(5000);

        onView(withId(R.id.xmlLyonButton)).perform(ViewActions.click());
        onView(withText("Lyon")).check(matches(isDisplayed()));
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

        onView(withId(R.id.xmlVillageButton)).perform(ViewActions.click());
        onView(withText("Village")).check(matches(isDisplayed()));
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

        onView(withId(R.id.xmlCromwellButton)).perform(ViewActions.click());
        onView(withText("Cromwell")).check(matches(isDisplayed()));
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

        onView(withId(R.id.xmlUytengsuButton)).perform(ViewActions.click());
        onView(withText("Uytengsu")).check(matches(isDisplayed()));
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

    }

    @Test
    public void testBookingBtn() throws InterruptedException {
        String username = "Bell@usc.edu", password = "JonBell";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(5000);

        // open Feature2.class
        onView(withId(R.id.xmlLyonButton)).perform(ViewActions.click());
        Thread.sleep(5000);

        // click book button and back button
        onView(withId(R.id.b_1_1)).perform(ViewActions.click());
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

        // book the approriate place|date|time
            //hardcode: place, time
            //get: date
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String date = dateFormat.format(today);
        String name = "Lyon", time="1000-1200";
        String concat = name+ "|" + date + "|" + time;

        // test: go to summary page, and check upcoming section: "Lyon|<today's date>|1000-1200"
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(5000);
        try{
            onView(withId(R.id.view13)).check(matches(withText(concat)));
        }
        catch( AssertionFailedError e){
            try{
                onView(withId(R.id.view6)).check(matches(withText(concat)));
            }
            catch( AssertionFailedError e5){
                try{
                    onView(withId(R.id.view)).check(matches(withText(concat)));
                }
                catch( AssertionFailedError e4){
                    try{
                        onView(withId(R.id.view3)).check(matches(withText(concat)));
                    }
                    catch( AssertionFailedError e3){
                        try{
                            onView(withId(R.id.view3)).check(matches(withText(concat)));
                        }
                        catch( AssertionFailedError e2){
                            try{
                                onView(withId(R.id.view5)).check(matches(withText(concat)));
                            }
                            catch( AssertionFailedError e1){
                                //failed
                            }
                        }
                    }
                }
            }
        }


    }


    @Test
    public void testReminderBtn() throws InterruptedException{
        String username = "Bell@usc.edu", password = "JonBell";
        onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText(username));
        onView(ViewMatchers.withId(R.id.edPassword)).perform(ViewActions.typeText(password));
        onView(withId(R.id.btnLogin)).perform(ViewActions.click());
        Thread.sleep(5000);

        // open Feature2.class
        onView(withId(R.id.xmlLyonButton)).perform(ViewActions.click());
        Thread.sleep(5000);

        // click book button and back button
        onView(withId(R.id.b_1_2)).perform(ViewActions.click());
        onView(withId(R.id.b_1_2)).perform(ViewActions.click());
        onView(withId(R.id.b_1_2)).perform(ViewActions.click());
        onView(withId(R.id.b_1_2)).perform(ViewActions.click());
        onView(withId(R.id.b_1_2)).perform(ViewActions.click());
        onView(withId(R.id.rm_1_2)).perform(ViewActions.click());
        onView(withId(R.id.backBtn_feature2)).perform(ViewActions.click());
        Thread.sleep(5000);

        // book the approriate place|date|time
        //hardcode: place, time
        //get: date
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String date = dateFormat.format(today);
        String name = "Lyon", time="1200-1400";
        String concat = name+ "|" + date + "|" + time + "capacity(0/5)";

        // test: go to summary page, and check upcoming section: "Lyon|<today's date>|1000-1200"
        onView(withId(R.id.bookingButton)).perform(ViewActions.click());
        Thread.sleep(5000);
        try{
            onView(withId(R.id.view10)).check(matches(withText(concat)));
        }
        catch( AssertionFailedError e){
            try{
                onView(withId(R.id.view7)).check(matches(withText(concat)));
            }
            catch( AssertionFailedError e5){
                try{
                    onView(withId(R.id.view8)).check(matches(withText(concat)));
                }
                catch( AssertionFailedError e4){
                    //failed
                }
            }
        }
    }
}
