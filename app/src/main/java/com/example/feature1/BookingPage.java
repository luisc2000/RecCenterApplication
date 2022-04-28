package com.example.feature1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This is the booking window
public class BookingPage extends AppCompatActivity
{
    public static final String TAG = "TAG";
    static FirebaseAuth fAuth;
    static FirebaseFirestore fStore;
    static Map<String, Object> lyonCenter = new HashMap<>();
    static ArrayList<DocumentReference> lyonList = new ArrayList<DocumentReference>();
    static Map<String, Object> hscCenter = new HashMap<>();
    static ArrayList<DocumentReference> hscList = new ArrayList<DocumentReference>();
    static Map<String, Object> villageCenter = new HashMap<>();
    static ArrayList<DocumentReference> villageList = new ArrayList<DocumentReference>();
    static Map<String, Object> aquaCenter = new HashMap<>();
    static ArrayList<DocumentReference> aquaList = new ArrayList<DocumentReference>();
    static Calendar calendar;
    static Date today, todayPlusOne, todayPlusTwo, todayPlusThree;
    static SimpleDateFormat sdf1;
    static DateFormat dateFormat;
    static String userID, today_String ,todayPlusOne_String, todayPlusTwo_String , todayPlusThree_String;
    static ArrayList<String> days = new ArrayList<String>();
    static Map<String, String> namesMap  = new HashMap<String, String>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Feature 3");

        boolean reminderFlag = false;

        setContentView(R.layout.activity_summary);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        createCalendar();

        //######################## BUILDS THE DATABASE ###############################
        /*
            if each day does not exist, then create it
         */
        //Lyon center
        DocumentReference gymsCollectionLyon = fStore.collection("Lyon_Center").document(days.get(0));
        DocumentReference gymsCollectionLyon1 = fStore.collection("Lyon_Center").document(days.get(1));
        DocumentReference gymsCollectionLyon2 = fStore.collection("Lyon_Center").document(days.get(2));
        DocumentReference gymsCollectionLyon3 = fStore.collection("Lyon_Center").document(days.get(3));

        lyonList.add(gymsCollectionLyon);
        lyonList.add(gymsCollectionLyon1);
        lyonList.add(gymsCollectionLyon2);
        lyonList.add(gymsCollectionLyon3);

        mapCreate(lyonCenter,"Lyon");
        List<String> list1 = new ArrayList<>();

        buildDatabase(fStore, "Lyon_Center", list1, lyonList, days, lyonCenter);
        //HSC center
        DocumentReference gymsCollectionHSC = fStore.collection("Cromwell_Center").document(days.get(0));
        DocumentReference gymsCollectionHSC1 = fStore.collection("Cromwell_Center").document(days.get(1));
        DocumentReference gymsCollectionHSC2= fStore.collection("Cromwell_Center").document(days.get(2));
        DocumentReference gymsCollectionHSC3= fStore.collection("Cromwell_Center").document(days.get(3));

        mapCreate(hscCenter,"Cromwell");
        hscList.add(gymsCollectionHSC);
        hscList.add(gymsCollectionHSC1);
        hscList.add(gymsCollectionHSC2);
        hscList.add(gymsCollectionHSC3);
        List<String> list2 = new ArrayList<>();

        buildDatabase(fStore, "Cromwell_Center", list2, hscList, days, hscCenter);

        //Village center
        DocumentReference gymsCollectionVillage = fStore.collection("Village_Center").document(days.get(0));
        DocumentReference gymsCollectionVillage1 = fStore.collection("Village_Center").document(days.get(1));
        DocumentReference gymsCollectionVillage2 = fStore.collection("Village_Center").document(days.get(2));
        DocumentReference gymsCollectionVillage3 = fStore.collection("Village_Center").document(days.get(3));

        mapCreate(villageCenter,"Village");
        villageList.add(gymsCollectionVillage);
        villageList.add(gymsCollectionVillage1);
        villageList.add(gymsCollectionVillage2);
        villageList.add(gymsCollectionVillage3);
        List<String> list3 = new ArrayList<>();

        buildDatabase(fStore, "Village_Center", list3, villageList, days, villageCenter);

        //Aquatics center
        DocumentReference gymsCollectionAqua = fStore.collection("Aqua_Center").document(days.get(0));
        DocumentReference gymsCollectionAqua1 = fStore.collection("Aqua_Center").document(days.get(1));
        DocumentReference gymsCollectionAqua2 = fStore.collection("Aqua_Center").document(days.get(2));
        DocumentReference gymsCollectionAqua3 = fStore.collection("Aqua_Center").document(days.get(3));

        mapCreate(aquaCenter,"Uytengsu");
        aquaList.add(gymsCollectionAqua);
        aquaList.add(gymsCollectionAqua1);
        aquaList.add(gymsCollectionAqua2);
        aquaList.add(gymsCollectionAqua3);
        List<String> list4 = new ArrayList<>();

        buildDatabase(fStore, "Aqua_Center", list4, aquaList, days, aquaCenter);

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
        createNameConversions("Village", "Uytengsu", "Cromwell", "Lyon");

        //TextView r1
        TextView r1 = (TextView)findViewById(R.id.view10);

        DocumentReference documentReference11 = fStore.collection("users").document(userID);
        populateReminder(documentReference11, "reminder_1", namesMap, r1);

        //Reminder2
        TextView r2 = (TextView)findViewById(R.id.view7);
        DocumentReference documentReference13 = fStore.collection("users").document(userID);
        populateReminder(documentReference13, "reminder_2", namesMap, r2);
        //Reminder 3
        TextView r3 = (TextView)findViewById(R.id.view8);
        DocumentReference documentReference15 = fStore.collection("users").document(userID);
        populateReminder(documentReference15, "reminder_3", namesMap, r3);

        //Reminder Delete Button section
        Button deleteRem1 = (Button) findViewById(R.id.button);
        deleteReminder(deleteRem1, documentReference, "reminder_1", r1);

        Button deleteRem2 = (Button) findViewById(R.id.button2);
        deleteReminder(deleteRem2, documentReference, "reminder_2", r2);

        Button deleteRem3 = (Button) findViewById(R.id.button3);
        deleteReminder(deleteRem3, documentReference, "reminder_3", r3);

        //First slot of upcoming appointment
        TextView tv2 = (TextView)findViewById(R.id.view);
        shift(documentReference, today, tv2, "Upcoming_Appt_1", "Previous_Appt_1");

        TextView tv3 = (TextView)findViewById(R.id.view3);
        shift(documentReference, today, tv3, "Upcoming_Appt_2", "Previous_Appt_2");

        TextView tv4 = (TextView)findViewById(R.id.view5);
        shift(documentReference, today, tv4, "Upcoming_Appt_3", "Previous_Appt_3");

        TextView tv5 = (TextView)findViewById(R.id.view6);
        shift(documentReference, today, tv5, "Upcoming_Appt_4", "Previous_Appt_4");

        TextView tv6 = (TextView)findViewById(R.id.view13);
        shift(documentReference, today, tv6, "Upcoming_Appt_5", "Previous_Appt_5");
        //Appt 1 delete
        Button delete2 = (Button) findViewById(R.id.button11);

        delete2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                upcomingApptDeleter(tv2, documentReference, "Upcoming_Appt_1", namesMap);
            }
        });

        //Appt 2 delete
        Button delete3 = (Button) findViewById(R.id.button13);
        delete3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                upcomingApptDeleter(tv3, documentReference, "Upcoming_Appt_2", namesMap);
            }
        });

        //Appt 3 delete
        Button delete4 = (Button) findViewById(R.id.button12);
        delete4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                upcomingApptDeleter(tv4, documentReference, "Upcoming_Appt_3", namesMap);
            }
        });

        //Appt 4 delete
        Button delete5 = (Button) findViewById(R.id.button14);
        delete5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                upcomingApptDeleter(tv5, documentReference, "Upcoming_Appt_4", namesMap);
            }
        });

        //Appt 5 delete
        Button delete6 = (Button) findViewById(R.id.button15);
        delete6.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                upcomingApptDeleter(tv6, documentReference, "Upcoming_Appt_5", namesMap);
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
        TextView tv74 = (TextView)findViewById(R.id.view42);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv74.setText(value.getString("Previous_Appt_3"));
            }
        });
        TextView tv75 = (TextView)findViewById(R.id.view20);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv75.setText(value.getString("Previous_Appt_4"));
            }
        });
        TextView tv76 = (TextView)findViewById(R.id.view41);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                tv76.setText(value.getString("Previous_Appt_5"));
            }
        });

        //This part is for clearing all previous bookings
        Button clear = (Button) findViewById(R.id.button4);
        clear.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //Objective: Update rec center capacity
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override

                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        tv7.setText("");
                        tv8.setText("");
                        tv74.setText("");
                        tv75.setText("");
                        tv76.setText("");
                        documentReference.update("Previous_Appt_1", "");
                        documentReference.update("Previous_Appt_2", "");
                        documentReference.update("Previous_Appt_3", "");
                        documentReference.update("Previous_Appt_4", "");
                        documentReference.update("Previous_Appt_5", "");
                    }
                });
            }
        });
//        if(MapPage.notifs[0] == true)
        if(MapPage.test == true)
        {
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
                {
                    String r1 = value.getString("reminder_1");
                    String r2 = value.getString("reminder_2");
                    String r3 = value.getString("reminder_3");

//                  now.getHour() + now.getMinute()
                    LocalTime now = LocalTime.now();

                    // view10

                    if(!r1.isEmpty())
                    {
                        String[] r1_array = r1.split("\\|");
                        if(r1_array[1].equals(today_String))
                        {
                            timeReminder(r1_array);
//                            TextView r1viewer = findViewById(R.id.view10);
//                            String tt = r1viewer.getText().toString();
//                            Toast.makeText(getApplicationContext(), "Reservation today: " + r1, Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!r2.isEmpty())
                    {
                        String[] r2_array = r2.split("\\|");
                        if(r2_array[1].equals(today_String))
                        {
                            timeReminder(r2_array);
//                            Toast.makeText(getApplicationContext(), "Reservation today: " + r2, Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!r3.isEmpty())
                    {
                        String[] r3_array = r3.split("\\|");
                        if(r3_array[1].equals(today_String))
                        {
                            timeReminder(r3_array);
//                            Toast.makeText(getApplicationContext(), "Reservation today: " + r3, Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        }

    } //No
    /*
        Creates a calendar based on the current day and populates three days ahead of it
     */
    static void createCalendar()
    {
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        sdf1 = new SimpleDateFormat("MMM dd, yyyy");
        try {
            today = sdf1.parse(sdf1.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateFormat = DateFormat.getDateInstance();
        today_String = dateFormat.format(today);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        todayPlusOne = calendar.getTime();
        todayPlusOne_String = dateFormat.format(todayPlusOne);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        todayPlusTwo = calendar.getTime();
        todayPlusTwo_String = dateFormat.format(todayPlusTwo);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        todayPlusThree = calendar.getTime();
        todayPlusThree_String = dateFormat.format(todayPlusThree);

        days.add(today_String);
        days.add(todayPlusOne_String);
        days.add(todayPlusTwo_String);
        days.add(todayPlusThree_String);
    }
    /**
     * Appends the correct name, time, and capacity to argument center passed in
     */
    static void mapCreate(Map<String, Object> centerZ, String string)
    {
        String capacity = "5";
        centerZ.put("name", string);
        centerZ.put("1000-1200", capacity);
        centerZ.put("1200-1400", capacity);
        centerZ.put("1400-1600", capacity);
    }

    static void createNameConversions(String name1, String name2, String name3, String name4)
    {
        namesMap  = new HashMap<String, String>();
        namesMap.put(name1, "Village_Center");
        namesMap.put(name2, "Aqua_Center");
        namesMap.put(name3,"Cromwell_Center");
        namesMap.put(name4, "Lyon_Center");
    }

    /**
     * Builds the actual database by updating days
     */
    static void buildDatabase(FirebaseFirestore fStore, String name_Center, List<String> list, ArrayList<DocumentReference> centerList, ArrayList days, Map mapCenter)
    {
        fStore.collection(name_Center).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        list.add(document.getId());
                    }
                    Log.d(TAG, list.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for(int i= 0; i<4; i++)
                {
                    if (!list.contains(days.get(i)))
                    {
                        centerList.get(i).set(mapCenter).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Loading gyms for" + userID);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * Puts information into the reminders field
     */
    void populateReminder(DocumentReference documentReference, String r_string, Map<String, String> namesMap, TextView tv)
    {
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String rem1 = task.getResult().getString(r_string);
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
                                                tv.setText(str[0] + " " + str[1] + " " + str[2] + " capacity" + "(" +capacityString+ "/5)");

                                            }
                                        });
                            }
                        }
                    }
                });
    }
    /**
     * Deletes information from the reminders field
     */
    public void upcomingApptDeleter(TextView tv, DocumentReference documentReference, String upcomingString, Map<String, String> namesMap)
    {
        tv.setText("");
        //Objective: Update rec center capacity
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String upcomingX = task.getResult().getString(upcomingString);
                        if( upcomingX != null)
                        {
                            String[] str =  upcomingX.split("\\|");
                            if(namesMap.get(str[0]) != null)
                            {
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
                                                documentReference.update(upcomingString,"");
                                            }
                                        });
                            }
                        }
                    }
                });
        documentReference.update(upcomingString,"");
    }
    /**
     * Shifts appointments who have expired into previous appointments
     */
    void shift(DocumentReference documentReference, Date today, TextView tv, String upcoming, String previous)
    {
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                String res = "";
                res = value.getString(upcoming); //Lyon|Mar 27, 2022|1000-1200
                if(res.isEmpty())
                {
                    //This is the case where the string is empty, so display nothing
                    tv.setText("");
                }
                else
                {
                    String[] str = res.split("\\|");
                    SimpleDateFormat sdf =new SimpleDateFormat("MMM dd, yyyy");
                    try {
                        Date date1 = sdf.parse(str[1]);
                        if(date1.before(today))
                        {
                            //If the date is outdated, send it to the previous bookings page
                            documentReference.update(upcoming, "");
                            documentReference.update(previous, res);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                tv.setText(value.getString(upcoming));
            }
        });
    }

    /**
     * Delete reminder buttons
     * @param button
     * @param documentReference
     * @param string
     * @param r
     */
    public void deleteReminder(Button button, DocumentReference documentReference, String string, TextView r)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                documentReference.update(string,"");
                r.setText("");
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void timeReminder(String[] r_array)
    {
        LocalTime now = LocalTime.now();
        if(r_array[2].equals("1000-1200"))
        {
            if(now.getHour() == 9) //9
            {
                Toast.makeText(getApplicationContext(), "Reservation in one hour: " + r_array[0] + " " + r_array[2], Toast.LENGTH_SHORT).show();
            }
        }
        if(r_array[2].equals("1200-1400"))
        {
            if(now.getHour() == 11)
            {
                Toast.makeText(getApplicationContext(), "Reservation in one hour: " + r_array[0] + " " + r_array[2], Toast.LENGTH_SHORT).show();
            }
        }
        if(r_array[2].equals("1400-1600"))
        {
            if(now.getHour() == 13)
            {
                Toast.makeText(getApplicationContext(), "Reservation in one hour: " + r_array[0] + " " + r_array[2], Toast.LENGTH_SHORT).show();
            }
        }
    }
} //No