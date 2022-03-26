package com.example.feature1;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//This is the booking window
public class BookingPage extends AppCompatActivity
{
    static ArrayList<String> kept = new ArrayList<String>();
    static ArrayList<String> cancelled = new ArrayList<String>();
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        //Building the gyms section of the database
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

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date todayPlusThree = calendar.getTime();
        String todayPlusThree_String = dateFormat.format(todayPlusThree);

        ArrayList<String> days = new ArrayList<String>();
        days.add(today_String);
        days.add(todayPlusOne_String);
        days.add(todayPlusTwo_String);
        days.add(todayPlusThree_String);

        //Lyon center
        DocumentReference gymsCollectionLyon = fStore.collection("Lyon_Center").document(days.get(0));
        DocumentReference gymsCollectionLyon1 = fStore.collection("Lyon_Center").document(days.get(1));
        DocumentReference gymsCollectionLyon2 = fStore.collection("Lyon_Center").document(days.get(2));
        DocumentReference gymsCollectionLyon3 = fStore.collection("Lyon_Center").document(days.get(3));
        Map<String, Object> lyonCenter = new HashMap<>();
        lyonCenter.put("name", "Lyon Center");
        lyonCenter.put("1000-1200", "5");
        lyonCenter.put("1200-1400", "5");
        lyonCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> lyonList = new ArrayList<DocumentReference>();
        lyonList.add(gymsCollectionLyon);
        lyonList.add(gymsCollectionLyon1);
        lyonList.add(gymsCollectionLyon2);
        lyonList.add(gymsCollectionLyon3);
        for(int i= 0; i<4; i++)
        {
            lyonList.get(i).set(lyonCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Loading gyms for" + userID);
                }
            });
        }

        //HSC center
        DocumentReference gymsCollectionHSC = fStore.collection("HSC_Center").document(days.get(0));
        DocumentReference gymsCollectionHSC1 = fStore.collection("HSC_Center").document(days.get(1));
        DocumentReference gymsCollectionHSC2= fStore.collection("HSC_Center").document(days.get(2));
        DocumentReference gymsCollectionHSC3= fStore.collection("HSC_Center").document(days.get(3));
        Map<String, Object> hscCenter = new HashMap<>();
        hscCenter.put("name", "HSC Center");
        hscCenter.put("1000-1200", "5");
        hscCenter.put("1200-1400", "5");
        hscCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> hscList = new ArrayList<DocumentReference>();
        hscList.add(gymsCollectionHSC);
        hscList.add(gymsCollectionHSC1);
        hscList.add(gymsCollectionHSC2);
        hscList.add(gymsCollectionHSC3);
        for(int i= 0; i<4; i++)
        {
            hscList.get(i).set(hscCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Loading gyms for" + userID);
                }
            });
        }

        //Village center
        DocumentReference gymsCollectionVillage = fStore.collection("Village_Center").document(days.get(0));
        DocumentReference gymsCollectionVillage1 = fStore.collection("Village_Center").document(days.get(1));
        DocumentReference gymsCollectionVillage2 = fStore.collection("Village_Center").document(days.get(2));
        DocumentReference gymsCollectionVillage3 = fStore.collection("Village_Center").document(days.get(3));
        Map<String, Object> villageCenter = new HashMap<>();
        villageCenter.put("name", "Village Center");
        villageCenter.put("1000-1200", "5");
        villageCenter.put("1200-1400", "5");
        villageCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> villageList = new ArrayList<DocumentReference>();
        villageList.add(gymsCollectionVillage);
        villageList.add(gymsCollectionVillage1);
        villageList.add(gymsCollectionVillage2);
        villageList.add(gymsCollectionVillage3);
        for(int i= 0; i<4; i++)
        {
            villageList.get(i).set(villageCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Loading gyms for" + userID);
                }
            });
        }

        //Aquatics center
        DocumentReference gymsCollectionAqua = fStore.collection("Aqua_Center").document(days.get(0));
        DocumentReference gymsCollectionAqua1 = fStore.collection("Aqua_Center").document(days.get(1));
        DocumentReference gymsCollectionAqua2 = fStore.collection("Aqua_Center").document(days.get(2));
        DocumentReference gymsCollectionAqua3 = fStore.collection("Aqua_Center").document(days.get(3));
        Map<String, Object> aquaCenter = new HashMap<>();
        aquaCenter.put("name", "Aquatics Center");
        aquaCenter.put("1000-1200", "5");
        aquaCenter.put("1200-1400", "5");
        aquaCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> aquaList = new ArrayList<DocumentReference>();
        aquaList.add(gymsCollectionAqua);
        aquaList.add(gymsCollectionAqua1);
        aquaList.add(gymsCollectionAqua2);
        aquaList.add(gymsCollectionAqua3);
        for(int i= 0; i<4; i++)
        {
            aquaList.get(i).set(aquaCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Loading gyms for" + userID);
                }
            });
        }

        //Users collection
        DocumentReference documentReference = fStore.collection("users").document(userID);

        //Back button
        Button myButton2 = (Button) findViewById(R.id.backSummary);
        myButton2.setBackgroundColor(Color.BLUE);
        myButton2.setTextColor(Color.WHITE);
        myButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_map_page);
                Intent nextScreen = new Intent(BookingPage.this, MapPage.class);
                startActivity(nextScreen);
            }
        });

        //This section is for updating pieces of the database
        documentReference.update("Upcoming_Appt_1","Lyon- Mar 13, 2023: 1000-1200");
        documentReference.update("Upcoming_Appt_2","Cromwell- Mar 28, 2023: 1400-1600");
        documentReference.update("Previous_Appt_1","Village- Mar 7, 2023: 0800-1000");

        TextView tv9 = (TextView)findViewById(R.id.textView);
        //This part of the code fetches info from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv9.setText("Welcome, " + value.getString("name"));
            }
        });

        //First slot of previous appointment
        TextView tv2 = (TextView)findViewById(R.id.view);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                //DocumentReference prev1 = fStore.collection("users").document(userID);
                tv2.setText(value.getString("Upcoming_Appt_1"));
            }
        });

        TextView tv3 = (TextView)findViewById(R.id.view3);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv3.setText(value.getString("Upcoming_Appt_2"));
            }
        });

        TextView tv4 = (TextView)findViewById(R.id.view5);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv4.setText(value.getString("Upcoming_Appt_3"));
            }
        });

        TextView tv5 = (TextView)findViewById(R.id.view6);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv5.setText(value.getString("Upcoming_Appt_4"));
            }
        });

        TextView tv6 = (TextView)findViewById(R.id.view13);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv6.setText(value.getString("Upcoming_Appt_5"));
            }
        });

        //Appt 1 delete
        Button delete2 = (Button) findViewById(R.id.button11);
        delete2.setBackgroundColor(Color.RED);
        delete2.setTextColor(Color.WHITE);
        delete2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv2.setText("");
                documentReference.update("Upcoming_Appt_1","");
            }
        });

        //Appt 2 delete
        Button delete3 = (Button) findViewById(R.id.button13);
        delete3.setBackgroundColor(Color.RED);
        delete3.setTextColor(Color.WHITE);
        delete3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv3.setText("");
                documentReference.update("Upcoming_Appt_2","");
            }
        });

        //Appt 3 delete
        Button delete4 = (Button) findViewById(R.id.button12);
        delete4.setBackgroundColor(Color.RED);
        delete4.setTextColor(Color.WHITE);
        delete4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv4.setText("");
                documentReference.update("Upcoming_Appt_3","");
            }
        });

        //Appt 4 delete
        Button delete5 = (Button) findViewById(R.id.button14);
        delete5.setBackgroundColor(Color.RED);
        delete5.setTextColor(Color.WHITE);
        delete5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv5.setText("");
                documentReference.update("Upcoming_Appt_4","");
            }
        });

        //Appt 5 delete
        Button delete6 = (Button) findViewById(R.id.button15);
        delete6.setBackgroundColor(Color.RED);
        delete6.setTextColor(Color.WHITE);
        delete6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv6.setText("");
                documentReference.update("Upcoming_Appt_5","");
            }
        });

        //Putting text in the previous bookings section
        TextView tv7 = (TextView)findViewById(R.id.view44);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv7.setText(value.getString("Previous_Appt_1"));
            }
        });

        TextView tv8 = (TextView)findViewById(R.id.view43);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv8.setText(value.getString("Previous_Appt_2"));
            }
        });

    } //No
} //No