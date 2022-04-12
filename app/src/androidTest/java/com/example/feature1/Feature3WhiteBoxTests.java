package com.example.feature1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

public class Feature3WhiteBoxTests
{
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Test
    public void testMaps()
    {
        BookingPage page = new BookingPage();
        //In my booking page class, int x = 1
//        System.out.println("The value of x is: " + page.x);
    }
}
