package com.example.feature1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName, eRegPassword;
    private Button eRegister;

    public static Credentials credentials;

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);

        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null)
//        {
//            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//            finish();
//        }
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if(validate(regUsername, regPassword))
                {
                    fAuth.createUserWithEmailAndPassword(regUsername, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration failed: " + task.getException(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
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