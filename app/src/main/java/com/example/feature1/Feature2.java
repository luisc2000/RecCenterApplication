package com.example.feature1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

public class Feature2 extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    String collection = "gyms";
    String document = "LyonCenter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature2);


        /* BACK BUTTON */
        Button backBtn_feature2 = findViewById(R.id.backBtn_feature2);
        backBtn_feature2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(Feature2.this, MapPage.class);
                startActivity(nextScreen);
            }
        });


        /* DISPLAY INFORMATION */
        TextView c_1_1 = findViewById(R.id.c_1_1);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("gyms").document("LyonCenter");
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String c_1_1_final = task.getResult().getString("1000-1200");
                            c_1_1_final = "capacity: " + c_1_1_final;
                            c_1_1.setText(c_1_1_final);



                        }
                        else{
                            c_1_1.setText("Results not found");
                        }
                    }
                });



        /* BOOK BUTTON */
        Button b_1_1 = findViewById(R.id.b_1_1);
        b_1_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // updating capacity# on db
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String c_1_1_res = task.getResult().getString("1000-1200");
                                int c_1_1_final = Integer.parseInt(c_1_1_res);

                                // todo do an If-Else for RemindME
                                if(c_1_1_res.equals("0")){
                                    // do nothing?
                                }
                                else{
                                    c_1_1_final-=1;

                                    final DocumentReference sfDocRef = fStore.collection(collection).document(document);
                                    int finalC_1_1_final = c_1_1_final;
                                    fStore.runTransaction(new Transaction.Function<Void>() {
                                        @Override
                                        public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                                            transaction.update(sfDocRef, "1000-1200", String.valueOf(finalC_1_1_final));

                                            // Success
                                            return null;
                                        }
                                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                    String str = "Capacity: " + String.valueOf(c_1_1_final);
                                    c_1_1.setText(str);
                                }
                            }
                        });
            }
        });
    }
}