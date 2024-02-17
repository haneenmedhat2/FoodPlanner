package com.example.foodplanner.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogin,btnSignup,btnGuest;
    ImageView btnGoogle;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    boolean guest=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);
        btnGoogle=findViewById(R.id.btnGmail);
        btnGuest=findViewById(R.id.btnGuest);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.hide();


        googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                        .build();

        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guest=false;
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                intent.putExtra("IS_GUEST",guest);
                startActivity(intent);
            }
        });

    }

    private void signIn() {
        Intent intent =googleSignInClient.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                goToHome();
            } catch (ApiException e) {
                Toast.makeText(WelcomeActivity.this, e.getMessage() ,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToHome() {
        finish();
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);


    }
}