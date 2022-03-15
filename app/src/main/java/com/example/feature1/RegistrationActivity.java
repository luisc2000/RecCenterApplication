package com.example.feature1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName, eRegPassword;
    private Button eRegister;

    public static Credentials credentials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if(validate(regUsername, regPassword))
                {
                    credentials = new Credentials(regUsername, regPassword);
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private boolean validate(String username, String password)
    {
        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Please enter a username and a password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}