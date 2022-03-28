package com.example.feature1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This is the booking window
public class BookingPage extends AppCompatActivity
{
    DocumentReference checker;
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String rem1, rem2, rem3, upcoming1, upcoming2, upcoming3, upcoming4, upcoming5;
    String[] values = new String[3];
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Feature 3");

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

        //######################## BUILDS THE DATABASE ###############################
        /*
        if each day does not exist, then create it
         */
        //Lyon center
        DocumentReference gymsCollectionLyon = fStore.collection("Lyon_Center").document(days.get(0));
        DocumentReference gymsCollectionLyon1 = fStore.collection("Lyon_Center").document(days.get(1));
        DocumentReference gymsCollectionLyon2 = fStore.collection("Lyon_Center").document(days.get(2));
        DocumentReference gymsCollectionLyon3 = fStore.collection("Lyon_Center").document(days.get(3));

        ArrayList<DocumentReference> lyonList = new ArrayList<DocumentReference>();
        lyonList.add(gymsCollectionLyon);
        lyonList.add(gymsCollectionLyon1);
        lyonList.add(gymsCollectionLyon2);
        lyonList.add(gymsCollectionLyon3);

        Map<String, Object> lyonCenter = new HashMap<>();
        lyonCenter.put("name", "Lyon");
        lyonCenter.put("1000-1200", "5");
        lyonCenter.put("1200-1400", "5");
        lyonCenter.put("1400-1600", "5");

        List<String> list1 = new ArrayList<>();
        fStore.collection("Lyon_Center").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        list1.add(document.getId());
                    }
                    Log.d(TAG, list1.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for(int i= 0; i<4; i++)
                {
                    if (!list1.contains(days.get(i)))
                    {
                        lyonList.get(i).set(lyonCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Loading gyms for" + userID);
                            }
                        });
                    }
                }
            }
        });

        //HSC center
        DocumentReference gymsCollectionHSC = fStore.collection("Cromwell_Center").document(days.get(0));
        DocumentReference gymsCollectionHSC1 = fStore.collection("Cromwell_Center").document(days.get(1));
        DocumentReference gymsCollectionHSC2= fStore.collection("Cromwell_Center").document(days.get(2));
        DocumentReference gymsCollectionHSC3= fStore.collection("Cromwell_Center").document(days.get(3));
        Map<String, Object> hscCenter = new HashMap<>();
        hscCenter.put("name", "Cromwell");
        hscCenter.put("1000-1200", "5");
        hscCenter.put("1200-1400", "5");
        hscCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> hscList = new ArrayList<DocumentReference>();
        hscList.add(gymsCollectionHSC);
        hscList.add(gymsCollectionHSC1);
        hscList.add(gymsCollectionHSC2);
        hscList.add(gymsCollectionHSC3);
        List<String> list2 = new ArrayList<>();
        fStore.collection("Cromwell_Center").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        list2.add(document.getId());
                    }
                    Log.d(TAG, list2.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for(int i= 0; i<4; i++)
                {
                    if (!list2.contains(days.get(i)))
                    {
                        hscList.get(i).set(hscCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Loading gyms for" + userID);
                            }
                        });
                    }
                }
            }
        });

        //Village center
        DocumentReference gymsCollectionVillage = fStore.collection("Village_Center").document(days.get(0));
        DocumentReference gymsCollectionVillage1 = fStore.collection("Village_Center").document(days.get(1));
        DocumentReference gymsCollectionVillage2 = fStore.collection("Village_Center").document(days.get(2));
        DocumentReference gymsCollectionVillage3 = fStore.collection("Village_Center").document(days.get(3));
        Map<String, Object> villageCenter = new HashMap<>();
        villageCenter.put("name", "Village");
        villageCenter.put("1000-1200", "5");
        villageCenter.put("1200-1400", "5");
        villageCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> villageList = new ArrayList<DocumentReference>();
        villageList.add(gymsCollectionVillage);
        villageList.add(gymsCollectionVillage1);
        villageList.add(gymsCollectionVillage2);
        villageList.add(gymsCollectionVillage3);
        List<String> list3 = new ArrayList<>();
        fStore.collection("Village_Center").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        list3.add(document.getId());
                    }
                    Log.d(TAG, list3.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for(int i= 0; i<4; i++)
                {
                    if (!list3.contains(days.get(i)))
                    {
                        villageList.get(i).set(villageCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Loading gyms for" + userID);
                            }
                        });
                    }
                }
            }
        });

        //Aquatics center
        DocumentReference gymsCollectionAqua = fStore.collection("Aqua_Center").document(days.get(0));
        DocumentReference gymsCollectionAqua1 = fStore.collection("Aqua_Center").document(days.get(1));
        DocumentReference gymsCollectionAqua2 = fStore.collection("Aqua_Center").document(days.get(2));
        DocumentReference gymsCollectionAqua3 = fStore.collection("Aqua_Center").document(days.get(3));
        Map<String, Object> aquaCenter = new HashMap<>();
        aquaCenter.put("name", "Uytengsu");
        aquaCenter.put("1000-1200", "5");
        aquaCenter.put("1200-1400", "5");
        aquaCenter.put("1400-1600", "5");
        ArrayList<DocumentReference> aquaList = new ArrayList<DocumentReference>();
        aquaList.add(gymsCollectionAqua);
        aquaList.add(gymsCollectionAqua1);
        aquaList.add(gymsCollectionAqua2);
        aquaList.add(gymsCollectionAqua3);
        List<String> list4 = new ArrayList<>();
        fStore.collection("Aqua_Center").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        list4.add(document.getId());
                    }
                    Log.d(TAG, list4.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for(int i= 0; i<4; i++)
                {
                    if (!list4.contains(days.get(i)))
                    {
                        aquaList.get(i).set(aquaCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Loading gyms for" + userID);
                            }
                        });
                    }
                }
            }
        });

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

        //This section is for testing pieces of the database
        /**
        documentReference.update("Upcoming_Appt_1","Lyon|Mar 28, 2022|1400-1600");
        documentReference.update("Upcoming_Appt_2","Cromwell|Mar 29, 2022|1400-1600");
        documentReference.update("Previous_Appt_1","Village|Mar 29, 2022|0800-1000");
        documentReference.update("reminder_1","Village|Mar 27, 2022|1000-1200");
        documentReference.update("reminder_2","Lyon|Mar 27, 2022|1000-1200");
        documentReference.update("reminder_3","Cromwell|Mar 28, 2022|1000-1200");

        Lyon|Mar 28, 2022|1000-1200
        Village|Mar 29, 2022|1000-1200
        Uytengsu|Mar 30, 2022|1200-1400
        Cromwell|Mar 31, 2022|1000-1200
        Village|Mar 30, 2022|1400-1600
         **/

        TextView tv9 = (TextView)findViewById(R.id.textView);
        //This part of the code fetches info from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv9.setText("Welcome, " + value.getString("name"));
            }
        });

        //Reminders section
        //Reminder 1
        Map<String, String> namesMap  = new HashMap<String, String>();
        namesMap.put("Village", "Village_Center");
        namesMap.put("Uytengsu", "Aqua_Center");
        namesMap.put("Cromwell","Cromwell_Center");
        namesMap.put("Lyon", "Lyon_Center");

        TextView r1 = (TextView)findViewById(R.id.view10);
        DocumentReference documentReference11 = fStore.collection("users").document(userID);
        documentReference11.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        rem1 = task.getResult().getString("reminder_1");
                        //The string I am parsing "Village|Mar 27, 2022|1000-1200"
                        if(rem1 != null) {
                            String[] str = rem1.split("\\|");
                            if(namesMap.get(str[0]) != null) {
                                DocumentReference documentReference = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                documentReference.get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                String capacityString = task.getResult().getString(str[2]);
                                                int capacityInt = Integer.parseInt(capacityString);
                                                if (capacityInt > 0) {
                                                    r1.setText(str[0] + " " + str[1] + " " + str[2] + " slots" + "(" +capacityString+ "/5)");
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
        //Reminder2
        TextView r2 = (TextView)findViewById(R.id.view7);
        DocumentReference documentReference13 = fStore.collection("users").document(userID);
        documentReference13.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        rem2 = task.getResult().getString("reminder_2");
                        if(rem2 != null)
                        {
                            String[] str = rem2.split("\\|");
                            if(namesMap.get(str[0]) != null)
                            {
                                DocumentReference documentReference = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                documentReference.get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                            {
                                                String capacityString = task.getResult().getString(str[2]);
                                                int capacityInt =Integer.parseInt(capacityString);
                                                if(capacityInt > 0)
                                                {
                                                    r2.setText(str[0] + " "+  str[1] + " "+ str[2] +" slots" + "(" +capacityString+ "/5)");
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
        //Reminder 3
        TextView r3 = (TextView)findViewById(R.id.view7);
        DocumentReference documentReference15 = fStore.collection("users").document(userID);
        documentReference15.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        rem3 = task.getResult().getString("reminder_3");
                        if(rem3 != null)
                        {
                            String[] str = rem3.split("\\|");
                            if(namesMap.get(str[0]) != null)
                            {
                                DocumentReference documentReference = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                documentReference.get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                            {
                                                String capacityString = task.getResult().getString(str[2]);
                                                int capacityInt =Integer.parseInt(capacityString);
                                                if(capacityInt > 0)
                                                {
                                                    r3.setText(str[0] + " "+  str[1] + " "+ str[2] +" slots" + "(" +capacityString+ "/5)");
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });

        //First slot of previous appointment
        TextView tv2 = (TextView)findViewById(R.id.view);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
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
        //############################# THIS CODE MAY NOT COMPLETE TEST LATER ######################################################
        //Appt 1 delete
        Button delete2 = (Button) findViewById(R.id.button11);
        delete2.setBackgroundColor(Color.RED);
        delete2.setTextColor(Color.WHITE);
        delete2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tv2.setText("");
                //Objective: Update rec center capacity
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                upcoming1 = task.getResult().getString("Upcoming_Appt_1");
                                if( upcoming1 != null)
                                {
                                    String[] str =  upcoming1.split("\\|");
                                    if(namesMap.get(str[0]) != null)
                                    {
                                        //Lyon_Center .... Mar 28, 2022=
                                        DocumentReference gymRef = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                        gymRef.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                                    {
                                                        String capacityString = task.getResult().getString(str[2]);
                                                        int capacityInt =Integer.parseInt(capacityString);
                                                        if(capacityInt < 5)
                                                        {
                                                            capacityInt++;
                                                        }
                                                        String setNewCapacity = String.valueOf(capacityInt);
                                                        gymRef.update(str[2], setNewCapacity);
                                                        documentReference.update("Upcoming_Appt_1","");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                documentReference.update("Upcoming_Appt_1","");
            }
        });

        //Appt 2 delete
        Button delete3 = (Button) findViewById(R.id.button13);
        delete3.setBackgroundColor(Color.RED);
        delete3.setTextColor(Color.WHITE);
        delete3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tv3.setText("");
                //Objective: Update rec center capacity
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                upcoming2 = task.getResult().getString("Upcoming_Appt_2");
                                if( upcoming2 != null)
                                {
                                    String[] str =  upcoming2.split("\\|");
                                    if(namesMap.get(str[0]) != null)
                                    {
                                        //"Upcoming_Appt_2","Cromwell|Mar 29, 2022|1400-1600"
                                        //Cromwell -> Cromwell_Center
                                        //Mar 29, 2022
                                        DocumentReference gymRef2 = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                        gymRef2.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                                    {
                                                        String capacityString = task.getResult().getString(str[2]);
                                                        int capacityInt =Integer.parseInt(capacityString);
                                                        if(capacityInt < 5)
                                                        {
                                                            capacityInt++;
                                                        }
                                                        String setNewCapacity = String.valueOf(capacityInt);
                                                        gymRef2.update(str[2], setNewCapacity);
                                                        documentReference.update("Upcoming_Appt_2","");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                documentReference.update("Upcoming_Appt_2","");
            }
        });

        //Appt 3 delete
        Button delete4 = (Button) findViewById(R.id.button12);
        delete4.setBackgroundColor(Color.RED);
        delete4.setTextColor(Color.WHITE);
        delete4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tv4.setText("");
                //Objective: Update rec center capacity
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                upcoming3 = task.getResult().getString("Upcoming_Appt_3");
                                if( upcoming3 != null)
                                {
                                    String[] str =  upcoming3.split("\\|");
                                    if(namesMap.get(str[0]) != null)
                                    {
                                        DocumentReference gymRef3 = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                        gymRef3.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                                    {
                                                        String capacityString = task.getResult().getString(str[2]);
                                                        int capacityInt =Integer.parseInt(capacityString);
                                                        if(capacityInt < 5)
                                                        {
                                                            capacityInt++;
                                                        }
                                                        String setNewCapacity = String.valueOf(capacityInt);
                                                        gymRef3.update(str[2], setNewCapacity);
                                                        documentReference.update("Upcoming_Appt_3","");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                documentReference.update("Upcoming_Appt_3","");
            }
        });

        //Appt 4 delete
        Button delete5 = (Button) findViewById(R.id.button14);
        delete5.setBackgroundColor(Color.RED);
        delete5.setTextColor(Color.WHITE);
        delete5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tv5.setText("");
                //Objective: Update rec center capacity
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                upcoming4 = task.getResult().getString("Upcoming_Appt_4");
                                if( upcoming4 != null)
                                {
                                    String[] str =  upcoming4.split("\\|");
                                    if(namesMap.get(str[0]) != null)
                                    {
                                        DocumentReference gymRef4 = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                        gymRef4.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                                    {
                                                        String capacityString = task.getResult().getString(str[2]);
                                                        int capacityInt =Integer.parseInt(capacityString);
                                                        if(capacityInt < 5)
                                                        {
                                                            capacityInt++;
                                                        }
                                                        String setNewCapacity = String.valueOf(capacityInt);
                                                        gymRef4.update(str[2], setNewCapacity);
                                                        documentReference.update("Upcoming_Appt_4","");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                documentReference.update("Upcoming_Appt_4","");
            }
        });

        //Appt 5 delete
        Button delete6 = (Button) findViewById(R.id.button15);
        delete6.setBackgroundColor(Color.RED);
        delete6.setTextColor(Color.WHITE);
        delete6.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tv6.setText("");
                //Objective: Update rec center capacity
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                upcoming5 = task.getResult().getString("Upcoming_Appt_5");
                                if( upcoming5 != null)
                                {
                                    String[] str =  upcoming5.split("\\|");
                                    if(namesMap.get(str[0]) != null)
                                    {
                                        DocumentReference gymRef5 = fStore.collection(namesMap.get(str[0])).document(str[1]);
                                        gymRef5.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                                    {
                                                        String capacityString = task.getResult().getString(str[2]);
                                                        int capacityInt =Integer.parseInt(capacityString);
                                                        if(capacityInt < 5)
                                                        {
                                                            capacityInt++;
                                                        }
                                                        String setNewCapacity = String.valueOf(capacityInt);
                                                        gymRef5.update(str[2], setNewCapacity);
                                                        documentReference.update("Upcoming_Appt_5","");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
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