package com.example.feature3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feature1.R;

//This is where the map class will be
public class Feature3MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

//        Button myButton = (Button) findViewById(R.id.bookingButton);
//        myButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                setContentView(R.layout.activity_summary);
//                Intent nextScreen = new Intent(Feature3MainActivity.this, CopyOfMainActivity.class);
//                startActivity(nextScreen);
//            }
//        });
    }
}