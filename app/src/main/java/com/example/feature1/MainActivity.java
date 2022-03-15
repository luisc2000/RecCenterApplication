package com.example.feature1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText eName, ePassword; // prefix e is in java class
    private Button eLogin;
    private TextView eRegister;

    FirebaseAuth fAuth;

    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.edPassword);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.tvRegister);
        fAuth = FirebaseAuth.getInstance();

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
                    fAuth.signInWithEmailAndPassword(inputName, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, MapPage.class));
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, "Registration failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    // validates a user in login page
//    private boolean validate(String name, String pass)
//    {
//        if(RegistrationActivity.credentials != null)
//        {
//            if(name.equals(RegistrationActivity.credentials.getUserName()) && pass.equals(RegistrationActivity.credentials.getPassword()))
//            {
//                return true;
//            }
//        }
//        return false;
//    }
}