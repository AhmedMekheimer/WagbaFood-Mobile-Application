package com.example.admin_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button btn1;
    Button btn2;
    Button btn3;
    FirebaseAuth auth;
    Button logoutbtn;
    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.etstatus1);
        editText2=findViewById(R.id.etstatus2);
        editText3=findViewById(R.id.etstatus3);


        btn1=findViewById(R.id.btnstatus1);
        btn2=findViewById(R.id.btnstatus2);
        btn3=findViewById(R.id.btnstatus3);

/*        auth = FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Log.d("CART", user.getUid());
        }*/

        //FireBase DataBase//
        database = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        DatabaseReference statusRef1 = database.getReference("Users").child("Sv3LIS1AR4arx1RQ9kCCBlYJpnS2").child("Order1").child("Order Status");

        DatabaseReference statusRef2 = database.getReference("Users").child("6CYflOsNdMbYxD7bWh63dPQI9o02").child("Order1").child("Order Status");

        DatabaseReference statusRef3 = database.getReference("Users").child("mMq9oMOgcQMJEzV7o0NMrIoOByN2").child("Order1").child("Order Status");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currstatus=editText1.getText().toString();
                statusRef1.setValue(currstatus);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currstatus=editText2.getText().toString();
                statusRef2.setValue(currstatus);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currstatus=editText3.getText().toString();
                statusRef3.setValue(currstatus);
            }
        });


        logoutbtn=findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });

    }
}