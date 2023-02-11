package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TrackingActivity extends AppCompatActivity {
    FirebaseDatabase databasetrack;
    String status;
    TextView tvstatus;

    FirebaseAuth auth;
    String uid="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        tvstatus=findViewById(R.id.status_tracking);

        //FireBase Auth//
        auth= FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Log.d("CART", user.getUid());
            uid=user.getUid();
        }


        //FireBase DataBase//
        databasetrack = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        DatabaseReference statusRef = databasetrack.getReference("Users").child(uid).child("Order1").child("Order Status");

        statusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                status= Objects.requireNonNull(snapshot.getValue()).toString();
                tvstatus.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}