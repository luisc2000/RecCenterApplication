package com.example.feature1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Feature2WhiteBoxTests {


    @Test
    public void testDbCollections() {
        Map<String,String> test = Feature2.testRecCenterNames();
        assertEquals("Lyon", test.get("Lyon_Center"));
        assertEquals("Uytengsu", test.get("Aqua_Center"));
        assertEquals("Cromwell", test.get("Cromwell_Center"));
        assertEquals("Village", test.get("Village_Center"));
    }

    @Test
    public void getCalendarDates() {
        ArrayList<String> document = new ArrayList<>();
        document = Feature2.getCalendarDates();

        //get today's date
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
        //end

        assertEquals(document.get(0), today_String);
        assertEquals(document.get(1), todayPlusOne_String);
        assertEquals(document.get(2), todayPlusTwo_String);

    }

    @Test
    public void testCalendarList() {
        ArrayList<String> document = Feature2.getCalendarDates();
        assertTrue(Feature2.testCalendarList(document));
    }

    @Test
    public void testInstantiationRecCenters(){
        Set<String> test = new HashSet<>();
        test = Feature2.instantiateRecCenters();
        assertTrue(test.contains("Lyon_Center"));
        assertTrue(test.contains("Cromwell_Center"));
        assertTrue(test.contains("Aqua_Center"));
        assertTrue(test.contains("Village_Center"));

    }

    @Test
    public void validateRecCenters(){
        Set<String> test = new HashSet<>();
        test = Feature2.instantiateRecCenters();
        assertTrue(Feature2.validateRecCenterCollection(test));
    }

}


