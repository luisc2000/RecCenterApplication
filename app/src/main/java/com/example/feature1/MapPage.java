package com.example.feature1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MapPage extends AppCompatActivity implements OnMapReadyCallback {

    final static boolean[] notifs = {false};
    static boolean test = false;
    TextView name, email, studentID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button elogout = findViewById(R.id.LogoutButton);

        name = findViewById(R.id.TVname);
        email = findViewById(R.id.TVemail);
        studentID = findViewById(R.id.TVstudentID);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("name"));
                email.setText(value.getString("email"));
                studentID.setText(value.getString("studentID"));
            }
        });

        elogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        // switch for Alex

        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);


        test = app_preferences.getBoolean("test", false);

        Switch mySwitch = findViewById(R.id.switch1);
        mySwitch.setChecked(test);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    boolean test2 = true;
                    SharedPreferences.Editor editor = app_preferences.edit();
                    editor.putBoolean("test", test2);
                    editor.commit();

                    Toast.makeText(getBaseContext(), "On", Toast.LENGTH_SHORT).show();
                } else {
                    boolean test2 = false;
                    SharedPreferences.Editor editor = app_preferences.edit();
                    editor.putBoolean("test", test2);
                    editor.commit();

                    Toast.makeText(getBaseContext(), "Off", Toast.LENGTH_SHORT).show();
                }
            }
            });

        ///Alex's booking button
        Button myButton = findViewById(R.id.bookingButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_summary);
                Intent nextScreen = new Intent(MapPage.this, BookingPage.class);
                startActivity(nextScreen);
            }
        });

        // Jadrian's Buttons here
        Button lyon = findViewById(R.id.xmlLyonButton);
        lyon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_summary);
                Intent nextScreen = new Intent(MapPage.this, Feature2.class);

                // added this to point to specific gym database -Jadrian
                Bundle b = new Bundle();
                b.putString("key", "Lyon_Center");
                nextScreen.putExtras(b);
                //

                startActivity(nextScreen);
            }
        });

        Button cromwell = findViewById(R.id.xmlCromwellButton);
        cromwell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_summary);
                Intent nextScreen = new Intent(MapPage.this, Feature2.class);
                Bundle b = new Bundle();
                b.putString("key", "Cromwell_Center");
                nextScreen.putExtras(b);
                startActivity(nextScreen);
            }
        });

        Button village = findViewById(R.id.xmlVillageButton);
        village.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_summary);
                Intent nextScreen = new Intent(MapPage.this, Feature2.class);
                Bundle b = new Bundle();
                b.putString("key", "Village_Center");
                nextScreen.putExtras(b);
                startActivity(nextScreen);
            }
        });

        Button uytengsu = findViewById(R.id.xmlUytengsuButton);
        uytengsu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_summary);
                Intent nextScreen = new Intent(MapPage.this, Feature2.class);
                Bundle b = new Bundle();
                b.putString("key", "Aqua_Center");
                nextScreen.putExtras(b);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        map = googleMap;
        LatLng village = new LatLng(34.02528414431285, -118.28585527462162);
        map.addMarker(new MarkerOptions().position(village).title("USC Village Fitness Center"));

        LatLng lyonCenter = new LatLng(34.02464874710005, -118.28839680318703);
        map.addMarker(new MarkerOptions().position(lyonCenter).title("Lyon Center"));

        LatLng cromwell = new LatLng(34.023527870061926, -118.28796920391058);
        map.addMarker(new MarkerOptions().position(cromwell).title("Cromwell Track & Field"));

        LatLng Uytengsu = new LatLng(34.02493427833876, -118.28570100872535);
        map.addMarker(new MarkerOptions().position(Uytengsu).title("Uytengsu Aquatics Center"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.02512659537994, -118.28701347865245), 16f));
    }
}