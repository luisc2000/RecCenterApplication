package com.example.feature1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Feature2 extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    String collection;
    ArrayList<String> document = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature2);

        // todo get day_1, day_2, day_3 's dates
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

        // add days to document arraylist
        document.add(today_String);
        document.add(todayPlusOne_String);
        document.add(todayPlusTwo_String);


        // todo populate variables: collection and []document
        Bundle b = getIntent().getExtras();
        collection = b.getString("key");

        /* todo BACK BUTTON */
        Button backBtn_feature2 = findViewById(R.id.backBtn_feature2);
        backBtn_feature2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(Feature2.this, MapPage.class);
                startActivity(nextScreen);
            }
        });

        /* todo DISPLAY REC CENTER */
        TextView title_feature2 = findViewById(R.id.title_feature2);


        /* todo display correct TITLE DATES */
        TextView date_1 = findViewById(R.id.date_1);
        TextView date_2 = findViewById(R.id.date_2);
        TextView date_3 = findViewById(R.id.date_3);
        date_1.setText(document.get(0));
        date_2.setText(document.get(1));
        date_3.setText(document.get(2));

        /* todo display CAPACITY */
        TextView c_1_1 = findViewById(R.id.c_1_1);
        TextView c_1_2 = findViewById(R.id.c_1_2);
        TextView c_1_3 = findViewById(R.id.c_1_3);
        Button b_1_1 = findViewById(R.id.b_1_1);
        Button b_1_2 = findViewById(R.id.b_1_2);
        Button b_1_3 = findViewById(R.id.b_1_3);
        Button b_2_1 = findViewById(R.id.b_2_1);
        Button b_2_2 = findViewById(R.id.b_2_2);
        Button b_2_3 = findViewById(R.id.b_2_3);
        Button b_3_1 = findViewById(R.id.b_3_1);
        Button b_3_2 = findViewById(R.id.b_3_2);
        Button b_3_3 = findViewById(R.id.b_3_3);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();



        DocumentReference documentReference = fStore.collection(collection).document(document.get(0));
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String c_1_1_final = task.getResult().getString("1000-1200");
                            c_1_1_final = "capacity: " + c_1_1_final;
                            c_1_1.setText(c_1_1_final);

                            String c_1_2_final = task.getResult().getString("1200-1400");
                            c_1_2_final = "capacity: " + c_1_2_final;
                            c_1_2.setText(c_1_2_final);

                            String c_1_3_final = task.getResult().getString("1400-1600");
                            c_1_3_final = "capacity: " + c_1_3_final;
                            c_1_3.setText(c_1_3_final);

                            title_feature2.setText(task.getResult().getString("name"));



                        }
                        else{
                            c_1_1.setText("Results not found");
                        }
                    }
                });

        TextView c_2_1 = findViewById(R.id.c_2_1);
        TextView c_2_2 = findViewById(R.id.c_2_2);
        TextView c_2_3 = findViewById(R.id.c_2_3);

        documentReference = fStore.collection(collection).document(document.get(1));
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String c_2_1_final = task.getResult().getString("1000-1200");
                            c_2_1_final = "capacity: " + c_2_1_final;
                            c_2_1.setText(c_2_1_final);

                            String c_2_2_final = task.getResult().getString("1200-1400");
                            c_2_2_final = "capacity: " + c_2_2_final;
                            c_2_2.setText(c_2_2_final);

                            String c_2_3_final = task.getResult().getString("1400-1600");
                            c_2_3_final = "capacity: " + c_2_3_final;
                            c_2_3.setText(c_2_3_final);
                        }
                        else{
                            c_2_1.setText("Results not found");
                        }
                    }
                });

        TextView c_3_1 = findViewById(R.id.c_3_1);
        TextView c_3_2 = findViewById(R.id.c_3_2);
        TextView c_3_3 = findViewById(R.id.c_3_3);

        documentReference = fStore.collection(collection).document(document.get(2));
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String c_3_1_final = task.getResult().getString("1000-1200");
                            c_3_1_final = "capacity: " + c_3_1_final;
                            c_3_1.setText(c_3_1_final);

                            String c_3_2_final = task.getResult().getString("1200-1400");
                            c_3_2_final = "capacity: " + c_3_2_final;
                            c_3_2.setText(c_3_2_final);

                            String c_3_3_final = task.getResult().getString("1400-1600");
                            c_3_3_final = "capacity: " + c_3_3_final;
                            c_3_3.setText(c_3_3_final);
                        }
                        else{
                            c_3_1.setText("Results not found");
                        }
                    }
                });




        /* todo BOOKING BUTTON */
        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference = documentReference;
        b_1_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String c_1_1_res = task.getResult().getString("1000-1200");
                                int c_1_1_final = Integer.parseInt(c_1_1_res);



                                // todo change color of Book Button for RemindME
                                if(c_1_1_res.equals("0")){
                                    ;
                                }
                                else {
                                    c_1_1_final -= 1;

                                    // updating capacity count on App
                                    updateDb(collection, document.get(0), "1000-1200", c_1_1_final);

                                    String str = "capacity: " + String.valueOf(c_1_1_final);
                                    c_1_1.setText(str);

                                    //Lyon Center|Mar 27, 2022|1000-1200
                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });


        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference1 = documentReference;
        b_1_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference1.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String c_1_2_res = task.getResult().getString("1200-1400");
                                int c_1_2_final = Integer.parseInt(c_1_2_res);

                                // todo do an If-Else for RemindME
                                if(c_1_2_res.equals("0")){
                                    ;
                                }
                                else {
                                    c_1_2_final -= 1;


                                    // todo
                                    updateDb(collection, document.get(0), "1200-1400", c_1_2_final);

                                    String str = "capacity: " + String.valueOf(c_1_2_final);
                                    c_1_2.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference2 = documentReference;
        b_1_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference2.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String c_1_3_res = task.getResult().getString("1400-1600");
                                int c_1_3_final = Integer.parseInt(c_1_3_res);

                                // todo do an If-Else for RemindME
                                if(c_1_3_res.equals("0")){
                                    ;
                                }
                                else {
                                    c_1_3_final -= 1;


                                    // todo
                                    updateDb(collection, document.get(0), "1400-1600", c_1_3_final);

                                    String str = "capacity: " + String.valueOf(c_1_3_final);
                                    c_1_3.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });


        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference3 = documentReference;
        b_2_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference3.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1000-1200");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(1), "1000-1200", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_2_1.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });


        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference4 = documentReference;
        b_2_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference4.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1200-1400");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(1), "1200-1400", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_2_2.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference5 = documentReference;
        b_2_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference5.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1400-1600");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(1), "1400-1600", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_2_3.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference6 = documentReference;
        b_3_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference6.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1000-1200");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(2), "1000-1200", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_3_1.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference7 = documentReference;
        b_3_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference7.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1200-1400");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(2), "1200-1400", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_3_2.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference8 = documentReference;
        b_3_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                finalDocumentReference8.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1400-1600");
                                int str_final = Integer.parseInt(str_res);

                                // todo do an If-Else for RemindME
                                if(str_res.equals("0")){
                                    ;
                                }
                                else {
                                    str_final -= 1;
                                    updateDb(collection, document.get(2), "1400-1600", str_final);
                                    String str = "capacity: " + String.valueOf(str_final);
                                    c_3_3.setText(str);

                                    // updating Upcoming_Appt_i in firebase
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateBooking(str2);
                                }
                            }
                        });
            }
        });

        /* todo REMINDME btn */
        Button rm_1_1 = findViewById(R.id.rm_1_1);
        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference10 = documentReference;
        rm_1_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference10.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1000-1200");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }

                            }
                        });
            }
        });
        Button rm_1_2 = findViewById(R.id.rm_1_2);
        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference11 = documentReference;
        rm_1_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference11.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1200-1400");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }

                            }
                        });
            }
        });
        Button rm_1_3 = findViewById(R.id.rm_1_3);
        documentReference = fStore.collection(collection).document(document.get(0));
        DocumentReference finalDocumentReference12 = documentReference;
        rm_1_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference12.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1400-1600");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(0);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }

                            }
                        });
            }
        });
        Button rm_2_1 = findViewById(R.id.rm_2_1);
        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference13 = documentReference;
        rm_2_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference13.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1000-1200");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }

                            }
                        });
            }
        });
        Button rm_2_2 = findViewById(R.id.rm_2_2);
        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference14 = documentReference;
        rm_2_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference14.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1200-1400");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }
                            }
                        });
            }
        });
        Button rm_2_3 = findViewById(R.id.rm_2_3);
        documentReference = fStore.collection(collection).document(document.get(1));
        DocumentReference finalDocumentReference15 = documentReference;
        rm_2_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference15.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1400-1600");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(1);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }
                            }
                        });
            }
        });
        Button rm_3_1 = findViewById(R.id.rm_3_1);
        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference16 = documentReference;
        rm_3_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference16.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1000-1200");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1000-1200";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }
                            }
                        });
            }
        });
        Button rm_3_2 = findViewById(R.id.rm_3_2);
        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference17 = documentReference;
        rm_3_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference17.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1200-1400");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1200-1400";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }
                            }
                        });
            }
        });
        Button rm_3_3 = findViewById(R.id.rm_3_3);
        documentReference = fStore.collection(collection).document(document.get(2));
        DocumentReference finalDocumentReference18 = documentReference;
        rm_3_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalDocumentReference18.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String str_res = task.getResult().getString("1400-1600");

                                if(str_res.equals("0")){
                                    String name = task.getResult().getString("name");
                                    String date = document.get(2);
                                    String time = "1400-1600";
                                    String str2 = name + "|" + date + "|" + time;
                                    updateReminder(str2);
                                }
                                else{
                                    ;
                                }
                            }
                        });
            }
        });


    }


    public void updateDb(String collection, String document, String id, int value){

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid(); //

        final DocumentReference sfDocRef = fStore.collection(collection).document(document);
        fStore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sfDocRef, id, String.valueOf(value));

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void updateBooking(String value){

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String s1 = task.getResult().getString("Upcoming_Appt_1");
                        String s2 = task.getResult().getString("Upcoming_Appt_2");
                        String s3 = task.getResult().getString("Upcoming_Appt_3");
                        String s4 = task.getResult().getString("Upcoming_Appt_4");
                        String s5 = task.getResult().getString("Upcoming_Appt_5");

                        String str = null;
                        if(s1.isEmpty()){
                            str = "Upcoming_Appt_1";
                        }
                        else if(s2.isEmpty()){
                            str = "Upcoming_Appt_2";
                        }
                        else if(s3.isEmpty()){
                            str = "Upcoming_Appt_3";
                        }
                        else if(s4.isEmpty()){
                            str = "Upcoming_Appt_4";
                        }
                        else if(s5.isEmpty()){
                            str = "Upcoming_Appt_5";
                        }else{
                            str = "Upcoming_Appt_1";
                        }


                        final DocumentReference sfDocRef = fStore.collection("users").document(userId);
                        String finalStr = str;
                        fStore.runTransaction(new Transaction.Function<Void>() {
                            @Override
                            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                                transaction.update(sfDocRef, finalStr, value);
                                return null; // Success
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {}
                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {}
                                });
                    }
                });
    }

    public void updateReminder(String value){

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String s1 = task.getResult().getString("reminder_1");
                        String s2 = task.getResult().getString("reminder_2");
                        String s3 = task.getResult().getString("reminder_3");

                        String str = null;
                        if(s1.isEmpty()){
                            str = "reminder_1";
                        }
                        else if(s2.isEmpty()){
                            str = "reminder_2";
                        }
                        else if(s3.isEmpty()){
                            str = "reminder_3";
                        }
                        else{
                            str = "reminder_1";
                        }


                        final DocumentReference sfDocRef = fStore.collection("users").document(userId);
                        String finalStr = str;
                        fStore.runTransaction(new Transaction.Function<Void>() {
                            @Override
                            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                                transaction.update(sfDocRef, finalStr, value);
                                return null; // Success
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {}
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {}
                        });
                    }
                });
    }
}