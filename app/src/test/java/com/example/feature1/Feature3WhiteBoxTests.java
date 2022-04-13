package com.example.feature1;

import static com.example.feature1.BookingPage.today_String;
import static org.junit.Assert.assertEquals;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Feature3WhiteBoxTests
{
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Test
    public void testCorrectDate()
    {
        BookingPage.calendar = Calendar.getInstance();
        BookingPage.today = BookingPage.calendar.getTime();
        BookingPage.sdf1 = new SimpleDateFormat("MMM dd, yyyy");
        try {
            BookingPage.today = BookingPage.sdf1.parse(BookingPage.sdf1.format(BookingPage.today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BookingPage.dateFormat = DateFormat.getDateInstance();
        BookingPage.today_String = BookingPage.dateFormat.format(BookingPage.today);
        BookingPage. calendar.add(Calendar.DAY_OF_YEAR, 1);
        BookingPage.todayPlusOne = BookingPage.calendar.getTime();
        BookingPage.todayPlusOne_String = BookingPage.dateFormat.format(BookingPage.todayPlusOne);
        BookingPage.calendar.add(Calendar.DAY_OF_YEAR, 1);
        BookingPage.todayPlusTwo = BookingPage.calendar.getTime();
        BookingPage.todayPlusTwo_String = BookingPage.dateFormat.format(BookingPage.todayPlusTwo);
        BookingPage.calendar.add(Calendar.DAY_OF_YEAR, 1);
        BookingPage.todayPlusThree = BookingPage.calendar.getTime();
        BookingPage.todayPlusThree_String = BookingPage.dateFormat.format(BookingPage.todayPlusThree);
        BookingPage.days.add(today_String);
        BookingPage.days.add(BookingPage.todayPlusOne_String);
        BookingPage.days.add(BookingPage.todayPlusTwo_String);
        BookingPage.days.add(BookingPage.todayPlusThree_String);

        //Time Comparison
        Calendar testCalendar = Calendar.getInstance();
        DateFormat testFormat = DateFormat.getDateInstance();
        Date testDay1 = testCalendar.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
        try {
            testDay1 = simple.parse(simple.format(testDay1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String testDay1_String = testFormat.format(testDay1);
        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay2 = testCalendar.getTime();
        String testDay2_String = testFormat.format(testDay2);
        print(testDay2_String);

        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay3 = testCalendar.getTime();
        String testDay3_String = testFormat.format(testDay3);
        print(testDay3_String);

        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay4 = testCalendar.getTime();
        String testDay4_String = testFormat.format(testDay4);
        print(testDay4_String);

        assertEquals(BookingPage.days.get(0), testDay1_String);
        assertEquals(BookingPage.days.get(1), testDay2_String);
        assertEquals(BookingPage.days.get(2), testDay3_String);
        assertEquals(BookingPage.days.get(3), testDay4_String);
    }

    /**
     * This is a helper function for debugging
     */
    public void print(String string)
    {
        System.out.println(string);
    }

}
