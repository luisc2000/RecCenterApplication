package com.example.feature1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName, ePassword; // prefix e is in java class
    private Button eLogin;
    private TextView eRegister;

//    Credentials credentials = new Credentials("name", "pass");
    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.edPassword);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.tvRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter details correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    isValid = validate(inputName, inputPassword);

                    if(!isValid)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // add the code to go to the new activity
                        Intent intent = new Intent(MainActivity.this, MapPage.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    // validates a user in login page
    private boolean validate(String name, String pass)
    {
        if(RegistrationActivity.credentials != null)
        {
            if(name.equals(RegistrationActivity.credentials.getUserName()) && pass.equals(RegistrationActivity.credentials.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
}