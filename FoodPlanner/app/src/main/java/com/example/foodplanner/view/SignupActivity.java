package com.example.foodplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
Button btnSignup;
    TextInputEditText userName,email,password,confirmPassword;
    String name,userEmail,userPassword,passConfirm;
    FirebaseAuth mAuth;
    TextView loginNowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth= FirebaseAuth.getInstance();
        btnSignup=findViewById(R.id.btnSignup);
        userName=findViewById(R.id.lSignNameInputEditText);
        email=findViewById(R.id.signEmalInputEditText);
        password=findViewById(R.id.signPassInputEditText);
        confirmPassword=findViewById(R.id.etSignPassConInputEditText);
         loginNowTv = findViewById(R.id.loginNowTv);
        SpannableString content = new SpannableString("Click to login");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        loginNowTv.setText(content);
       ActionBar actionBar = getSupportActionBar ();
        actionBar.hide();


        loginNowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail= String.valueOf(email.getText());
                userPassword= String.valueOf(password.getText());
                name= String.valueOf(userName.getText());
                passConfirm= String.valueOf(confirmPassword.getText());

                    if(name.isEmpty()){
                    userName.setError("Please enter your Name");
                    userName.requestFocus();
                }

                    else  if(userEmail.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();

                }
                 else if(userPassword.isEmpty()){
                    password.setError("Please enter your Pasword");
                    password.requestFocus();
                }

               else if(passConfirm.isEmpty()){
                    confirmPassword.setError("Please confirm your password");
                    confirmPassword.requestFocus();
                }
               else if(!userPassword.equals(passConfirm)){
                        confirmPassword.setError("Password ConfirmPassword does not match");
                        confirmPassword.requestFocus();
                    }

              else{
                  mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, "Account is Created Successfuly.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(SignupActivity.this,task.getException().getMessage() ,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }
}