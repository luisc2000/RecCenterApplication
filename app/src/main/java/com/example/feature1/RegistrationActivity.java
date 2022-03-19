package com.example.feature1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText eRegName, eRegPassword, eFullName, ePhotoURL, eStudentID;
    private Button eRegister;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);
        eFullName = findViewById(R.id.etFullName);
        ePhotoURL = findViewById(R.id.etPhotoURL);
        eStudentID = findViewById(R.id.etStudentID);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String regUsername = eRegName.getText().toString();
                final String regPassword = eRegPassword.getText().toString();
                final String regFullName = eFullName.getText().toString();
                final String regPhotoURL = ePhotoURL.getText().toString();
                final String regStudentID = eStudentID.getText().toString();

                if(validate(regUsername, regPassword))
                {
                    fAuth.createUserWithEmailAndPassword(regUsername, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", regFullName);
                                user.put("email", regUsername);
                                user.put("password", regPassword);
                                user.put("photoURL", regPhotoURL);
                                user.put("studentID", regStudentID);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                    }
                                });
                                startActivity(new Intent(RegistrationActivity.this, MapPage.class));
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