package com.example.wagba_application;

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

public class LoginActivity extends AppCompatActivity {

    EditText usernametxtin;
    EditText passwordtxtin;
    Button loginbtn;
    TextView tvsignuphere;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernametxtin = findViewById(R.id.usernamelog);
        passwordtxtin = findViewById(R.id.passwordlog);
        loginbtn = findViewById(R.id.loginbtn);
        tvsignuphere = findViewById(R.id.tvsignuphere);

        auth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(view -> {
            LoginUser();
        });

        tvsignuphere.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
//        signInButton=findViewById(R.id.signin_btn);
//
//        GoogleSignInOptions googleSignInOptions =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
    }

    private void LoginUser()
    {
        String username=usernametxtin.getText().toString();
        String password=passwordtxtin.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            usernametxtin.setError("Email can't be Empty");
            usernametxtin.requestFocus();
        }else if(TextUtils.isEmpty(password))
        {
            passwordtxtin.setError("Password can't be Empty");
            passwordtxtin.requestFocus();
        }else{
            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,"User LogIn Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));

                    }else {
                        Toast.makeText(LoginActivity.this,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}