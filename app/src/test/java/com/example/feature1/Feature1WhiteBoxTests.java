package com.example.feature1;

import static org.junit.Assert.assertEquals;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

public class Feature1WhiteBoxTests{
    String username = "", password = "";
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String userID = "R4Gtu8xdrDbbriUrMvaoONYRYRh2";

//    fStore.collection("users").document(userID).get()
//            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//        @Override
//        public void onSuccess(DocumentSnapshot documentSnapshot) {
//            documentSnapshot.toString().getString("name");
//        }
//    });
//}


    @Test
    public void checkUsername() // at the beginning the string should be empty
    {
        assertEquals("", username);
    }

    @Test
    public void checkPassword() // at the beginning the string should be empty
    {
        assertEquals("", password);
    }

    String usernamePopulated = "JonBell@usc.edu", passwordPopulated = "JonBell";
    @Test
    public void checkUsernamePopulated() // at the beginning the string should be empty
    {
        assertEquals("JonBell@usc.edu", usernamePopulated);
    }

    @Test
    public void checkPasswordPopulated() // at the beginning the string should be empty
    {
        assertEquals("JonBell", passwordPopulated);
    }
}
