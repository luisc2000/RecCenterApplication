package com.example.feature1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public EditText eName, ePassword; // prefix e is in java class
    public Button eLogin;
    public TextView eRegister;
    FirebaseAuth fAuth;
    static String inputName = "Bell@usc.edu", inputPassword = "JonBell", invalid = "";

    static boolean emailAndPasswordSet(String email, String password)
    {
        if(!email.isEmpty() && !password.isEmpty())
        {
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.edPassword);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.tvRegister);
        fAuth = FirebaseAuth.getInstance();

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName = eName.getText().toString();
                inputPassword = ePassword.getText().toString();

                if(!emailAndPasswordSet(inputName, inputPassword)) // not set
                {
                    Toast.makeText(getApplicationContext(), "Please enter details correctly", Toast.LENGTH_SHORT).show();
                }
                else // fields are set
                {
                    fAuth.signInWithEmailAndPassword(inputName, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MapPage.class));
                            }
                            else
                            {
                                String toastMessage = "Login failed: " + task.getException();
                                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}