package com.example.feature1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.Transaction;


public class Fragment1 extends Fragment implements View.OnClickListener{

    // Capacity for Monday
    TextView cap_1_1, cap_1_2, cap_1_3, cap_1_4, cap_1_5, cap_1_6, recName_1;
    int count = 10;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        // first capacity for Monday -test
//        TextView cap_1_1 = getActivity().findViewById(R.id.cap_1_1);
//        cap_1_1.setText("Capacity: 10"); (this works)

        cap_1_1 = getActivity().findViewById(R.id.cap_1_1);
//        String str = "Capacity: " + String.valueOf(count);
//        cap_1_1.setText(str);

        cap_1_2 = getActivity().findViewById(R.id.cap_1_2);
        cap_1_3 = getActivity().findViewById(R.id.cap_1_3);
        cap_1_4 = getActivity().findViewById(R.id.cap_1_4);
        cap_1_5 = getActivity().findViewById(R.id.cap_1_5);
        cap_1_6 = getActivity().findViewById(R.id.cap_1_6);
        recName_1 = getActivity().findViewById(R.id.recName_1);



        // BACK btn for Monday
        Button backBtn_1 = getActivity().findViewById(R.id.backBtn_1);
        backBtn_1.setOnClickListener(this);

        // BOOK buttons for Monday
        Button bkBtn_1_1 = getActivity().findViewById(R.id.bkBtn_1_1);
        bkBtn_1_1.setOnClickListener(this);

        // REMIND ME button for Monday
        Button rmBtn_1_1 = getActivity().findViewById(R.id.rmBtn_1_1);
        rmBtn_1_1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid(); // todo NullPointerException

        DocumentReference documentReference = fStore.collection("gyms").document("LyonCenter");
            // document references the specific "gyms" (second column in firebase)
            // userID (test@yahoo.com) points to the 3rd "gyms" (Aquatic Center with 0600-0659)


        Intent intent;
        switch(view.getId()){
            case R.id.backBtn_1:
                intent = new Intent(getActivity(), MapPage.class);
                startActivity(intent);
                break;
            case R.id.bkBtn_1_1:
                // updating capacity# on db
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String cap_1_1_res = task.getResult().getString("1000-1200");
                                int cap_1_1_final = Integer.parseInt(cap_1_1_res);

                                // todo do an If-Else for RemindME
                                if(cap_1_1_res.equals("0")){
                                    // do nothing?
                                }
                                else{
                                    cap_1_1_final-=1;
                                    updateDb("gyms", "LyonCenter", "1000-1200", cap_1_1_final);
                                    String str = "Capacity: " + String.valueOf(cap_1_1_final);
                                    cap_1_1.setText(str);
                                }
                            }
                        });

                break;
            case R.id.rmBtn_1_1:
//                Toast.makeText(Fragment1.this, "remind me", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid(); // todo NullPointerException

        DocumentReference documentReference = fStore.collection("gyms").document("LyonCenter");

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String cap_1_1_res = task.getResult().getString("1000-1200");
                            String cap_1_2_res = task.getResult().getString("1200-1400");
                            String cap_1_3_res = task.getResult().getString("1400-1600");
                            String cap_1_4_res = task.getResult().getString("1600-1800");
                            String cap_1_5_res = task.getResult().getString("1800-2000");
                            String cap_1_6_res = task.getResult().getString("2000-2200");

                            String cap_1_1_final = "Capacity: " + cap_1_1_res;
                            String cap_1_2_final = "Capacity: " + cap_1_2_res;
                            String cap_1_3_final = "Capacity: " + cap_1_3_res;
                            String cap_1_4_final = "Capacity: " + cap_1_4_res;
                            String cap_1_5_final = "Capacity: " + cap_1_5_res;
                            String cap_1_6_final = "Capacity: " + cap_1_6_res;

                            cap_1_1.setText(cap_1_1_final);
                            cap_1_2.setText(cap_1_2_final);
                            cap_1_3.setText(cap_1_3_final);
                            cap_1_4.setText(cap_1_4_final);
                            cap_1_5.setText(cap_1_5_final);
                            cap_1_6.setText(cap_1_6_final);

                            recName_1.setText(task.getResult().getString("name"));
                        }
                        else{
                            cap_1_1.setText("Results not found");
                        }
                    }
                });

    }

    public void updateDb(String collection, String document, String id, int value){

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid(); // todo NullPointerException

        final DocumentReference sfDocRef = fStore.collection(collection).document(document);
        fStore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sfDocRef, id, String.valueOf(value));

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
    }

}
