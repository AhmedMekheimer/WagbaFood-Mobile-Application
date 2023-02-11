package com.example.admin_application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUpActivity extends AppCompatActivity {

    EditText usernametxtup;
    EditText passwordtxtup;
    Button signupbtn;
    TextView tvLoginhere;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernametxtup=findViewById(R.id.usernameup);
        passwordtxtup=findViewById(R.id.passwordup);
        signupbtn=findViewById(R.id.signupbtn);
        tvLoginhere=findViewById(R.id.tvloginhere);

        auth =FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(view ->{
            createUser();
        });

        tvLoginhere.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        });
    }

    private void createUser(){
        String username=usernametxtup.getText().toString();
        String password=passwordtxtup.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            usernametxtup.setError("Email can't be Empty");
            usernametxtup.requestFocus();
        }else if(TextUtils.isEmpty(password))
        {
            passwordtxtup.setError("Password can't be Empty");
            passwordtxtup.requestFocus();
        } else{
            auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this,"User SignedUp Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(SignUpActivity.this,"SignUp Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}