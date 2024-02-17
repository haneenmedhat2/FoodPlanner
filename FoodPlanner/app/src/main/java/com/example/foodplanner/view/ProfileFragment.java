package com.example.foodplanner.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.homeScreen.view.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    Button btnLogout;
    TextView userName;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();

        mAuth= FirebaseAuth.getInstance();
        btnLogout =view.findViewById(R.id.btnLogout);
        userName= view.findViewById(R.id.tvLogout);
        user=mAuth.getCurrentUser();
        if(user== null){
            Navigation.findNavController(view).navigate(R.id.action_profileFragment2_to_homeFragment2);
            Toast.makeText(getContext(), "Your are not authorized .", Toast.LENGTH_SHORT).show();
        }else{
            userName.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), WelcomeActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "logged out successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
}