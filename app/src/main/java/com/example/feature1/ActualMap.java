package com.example.feature1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActualMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    String sample = "I am a very long string with too many words I am a very long string with too many words I am a very long string with too many words";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        map = googleMap;
        LatLng village = new LatLng(34.02528414431285, -118.28585527462162);
        map.addMarker(new MarkerOptions().position(village).title("USC Village Fitness Center")/*.snippet(sample)*/);

        LatLng lyonCenter = new LatLng(34.02464874710005, -118.28839680318703);
        map.addMarker(new MarkerOptions().position(lyonCenter).title("Lyon Center"));

        LatLng cromwell = new LatLng(34.023527870061926, -118.28796920391058);
        map.addMarker(new MarkerOptions().position(cromwell).title("Cromwell Track & Field"));

        LatLng Uytengsu = new LatLng(34.02493427833876, -118.28570100872535);
        map.addMarker(new MarkerOptions().position(Uytengsu).title("Uytengsu Aquatics Center"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lyonCenter, 16f));
    }

}