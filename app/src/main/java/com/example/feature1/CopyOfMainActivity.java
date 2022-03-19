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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//This is the booking window
public class CopyOfMainActivity extends AppCompatActivity
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
        DocumentReference gymsCollection = fStore.collection("gyms").document(userID);
        //Lyon center
        Map<String, Object> lyonCenter = new HashMap<>();
        lyonCenter.put("name", "Lyon Center");
        lyonCenter.put("0600-0659", "0");
        lyonCenter.put("0700-0759", "0");
        lyonCenter.put("0800-0859", "0");
        lyonCenter.put("0900-0959", "0");
        lyonCenter.put("1000-1059", "0");
        lyonCenter.put("1100-1159", "0");
        lyonCenter.put("1200-1259", "0");
        lyonCenter.put("1300-1359", "0");
        lyonCenter.put("1400-1459", "0");
        lyonCenter.put("1500-1559", "0");
        lyonCenter.put("1600-1659", "0");
        lyonCenter.put("1700-1759", "0");
        lyonCenter.put("1800-1859", "0");
        lyonCenter.put("1900-1959", "0");
        lyonCenter.put("2000-2059", "0");
        lyonCenter.put("2100-2159", "0");
        lyonCenter.put("2200-2259", "0");
        lyonCenter.put("2300-2359", "0");

        //HSC center
        Map<String, Object> hscCenter = new HashMap<>();
        hscCenter.put("name", "HSC Center");
        hscCenter.put("0600-0659", "0");
        hscCenter.put("0700-0759", "0");
        hscCenter.put("0800-0859", "0");
        hscCenter.put("0900-0959", "0");
        hscCenter.put("1000-1059", "0");
        hscCenter.put("1100-1159", "0");
        hscCenter.put("1200-1259", "0");
        hscCenter.put("1300-1359", "0");
        hscCenter.put("1400-1459", "0");
        hscCenter.put("1500-1559", "0");
        hscCenter.put("1600-1659", "0");
        hscCenter.put("1700-1759", "0");
        hscCenter.put("1800-1859", "0");
        hscCenter.put("1900-1959", "0");
        hscCenter.put("2000-2059", "0");
        hscCenter.put("2100-2159", "0");
        hscCenter.put("2200-2259", "0");
        hscCenter.put("2300-2359", "0");

        //Village center
        Map<String, Object> villageCenter = new HashMap<>();
        hscCenter.put("name", "Village Center");
        villageCenter.put("0600-0659", "0");
        villageCenter.put("0700-0759", "0");
        villageCenter.put("0800-0859", "0");
        villageCenter.put("0900-0959", "0");
        villageCenter.put("1000-1059", "0");
        villageCenter.put("1100-1159", "0");
        villageCenter.put("1200-1259", "0");
        villageCenter.put("1300-1359", "0");
        villageCenter.put("1400-1459", "0");
        villageCenter.put("1500-1559", "0");
        villageCenter.put("1600-1659", "0");
        villageCenter.put("1700-1759", "0");
        villageCenter.put("1800-1859", "0");
        villageCenter.put("1900-1959", "0");
        villageCenter.put("2000-2059", "0");
        villageCenter.put("2100-2159", "0");
        villageCenter.put("2200-2259", "0");
        villageCenter.put("2300-2359", "0");

        //Aquatics center
        Map<String, Object> aquaCenter = new HashMap<>();
        aquaCenter.put("name", "Aquatics Center");
        aquaCenter.put("0600-0659", "0");
        aquaCenter.put("0700-0759", "0");
        aquaCenter.put("0800-0859", "0");
        aquaCenter.put("0900-0959", "0");
        aquaCenter.put("1000-1059", "0");
        aquaCenter.put("1100-1159", "0");
        aquaCenter.put("1200-1259", "0");
        aquaCenter.put("1300-1359", "0");
        aquaCenter.put("1400-1459", "0");
        aquaCenter.put("1500-1559", "0");
        aquaCenter.put("1600-1659", "0");
        aquaCenter.put("1700-1759", "0");
        aquaCenter.put("1800-1859", "0");
        aquaCenter.put("1900-1959", "0");
        aquaCenter.put("2000-2059", "0");
        aquaCenter.put("2100-2159", "0");
        aquaCenter.put("2200-2259", "0");
        aquaCenter.put("2300-2359", "0");

        gymsCollection.set(lyonCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Loading gyms for" + userID);
            }
        });
        gymsCollection.set(hscCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Loading gyms for" + userID);
            }
        });
        gymsCollection.set(villageCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Loading gyms for" + userID);
            }
        });
        gymsCollection.set(aquaCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Loading gyms for" + userID);
            }
        });

        //Back button
        Button myButton2 = (Button) findViewById(R.id.backSummary);
        myButton2.setBackgroundColor(Color.BLUE);
        myButton2.setTextColor(Color.WHITE);
        myButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_map_page);
                Intent nextScreen = new Intent(CopyOfMainActivity.this, MapPage.class);
                startActivity(nextScreen);
            }
        });

        TextView tv9 = (TextView)findViewById(R.id.textView);
        tv9.setText("Welcome, " + userID);

        //Appending text to the text views
        TextView tv1 = (TextView)findViewById(R.id.view);
        gymsCollection.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tv1.setText(value.getString("1800-1859"));
            }
        });

        TextView tv2 = (TextView)findViewById(R.id.view);
        tv2.setText("1500-600, January 29th");

        TextView tv3 = (TextView)findViewById(R.id.view3);
        tv3.setText("1400-1500, February 13th");

        TextView tv4 = (TextView)findViewById(R.id.view5);
        tv4.setText("1500-1600,April 16th");

        TextView tv5 = (TextView)findViewById(R.id.view6);
        tv5.setText("0800-0900, January 13th");

        TextView tv6 = (TextView)findViewById(R.id.view13);
        tv6.setText("1700-1800, January 3rd");


        Button delete2 = (Button) findViewById(R.id.button11);
        delete2.setBackgroundColor(Color.RED);
        delete2.setTextColor(Color.WHITE);
        delete2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv2.setText("");
            }
        });

        Button delete3 = (Button) findViewById(R.id.button13);
        delete3.setBackgroundColor(Color.RED);
        delete3.setTextColor(Color.WHITE);
        delete3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv3.setText("");
            }
        });

        Button delete4 = (Button) findViewById(R.id.button12);
        delete4.setBackgroundColor(Color.RED);
        delete4.setTextColor(Color.WHITE);
        delete4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv4.setText("");
            }
        });

        Button delete5 = (Button) findViewById(R.id.button14);
        delete5.setBackgroundColor(Color.RED);
        delete5.setTextColor(Color.WHITE);
        delete5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv5.setText("");
            }
        });

        Button delete6 = (Button) findViewById(R.id.button15);
        delete6.setBackgroundColor(Color.RED);
        delete6.setTextColor(Color.WHITE);
        delete6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv6.setText("");
            }
        });

        //Putting text in the previous bookings section
        TextView tv7 = (TextView)findViewById(R.id.view44);
        tv7.setText("1300-1500, June 16th");

        TextView tv8 = (TextView)findViewById(R.id.view43);
        tv8.setText("2000-2100, June 19th");

    } //No
} //No