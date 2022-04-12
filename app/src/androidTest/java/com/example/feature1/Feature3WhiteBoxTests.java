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
        System.out.println("------here: " + BookingPage.x);
    }
}
