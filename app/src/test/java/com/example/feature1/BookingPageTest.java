package com.example.feature1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

public class BookingPageTest
{
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Test
    public void buildDatabase()
    {
//        DocumentReference documentReference = fStore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
//            {
//                BookingPage page = new BookingPage();
//                page.r1.setText(value.getString("Lyon|Mar 29, 2022|1200-1400"));
//            }
//        });



//        BookingPage page = new BookingPage();
//        boolean hi = true;
//        if(!(page == null))
//        {
//            assertEquals(4, 2 + 2);
//        }



//        page.r1.setText(value.getString("Lyon|Mar 29, 2022|1200-1400"));
    }

    @Test
    public void populateReminder() {
    }

    @Test
    public void upcomingApptDeleter() {
    }

    @Test
    public void shift() {
    }

    @Test
    public void deleteReminder() {
    }
}