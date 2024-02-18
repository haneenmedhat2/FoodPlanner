package com.example.foodplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email,password;
    String userEmail,userPassword;
    FirebaseAuth mAuth;
    TextView forgetPassword;
    Button btnLogin;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth= FirebaseAuth.getInstance();
        btnLogin =findViewById(R.id.btnLogin);
        email=findViewById(R.id.logEmalInputEditText);
        password=findViewById(R.id.logPassInputEditText);
        forgetPassword = findViewById(R.id.tvForget);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);*/
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail= String.valueOf(email.getText());
                userPassword= String.valueOf(password.getText());

                 if(userEmail.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();

                }

               else if(userPassword.isEmpty()){
                    email.setError("Please enter your password");
                    email.requestFocus();

                }else{

                mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage() ,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }







            }
        });

    }
}