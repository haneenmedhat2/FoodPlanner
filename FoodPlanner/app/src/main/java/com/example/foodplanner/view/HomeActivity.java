package com.example.foodplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigator_layout);
        bottomNavigationView=findViewById(R.id.btm_nav);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu) ;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        navController = Navigation. findNavController( this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(WelcomeActivity.guest==true){
                    if (item.getItemId() == R.id.searchFragment2){
                        navController.navigate(R.id.searchFragment2);
                    }
                    if (item.getItemId() == R.id.homeFragment2){
                        navController.navigate(R.id.homeFragment2);
                    }
                    if (item.getItemId() == R.id.favouriteFragment2){
                        navController.navigate(R.id.favouriteFragment2);
                    }
                    if (item.getItemId() == R.id.weaklyPlanFragment){
                        navController.navigate(R.id.weaklyPlanFragment);
                    }
                    if (item.getItemId() == R.id.profileFragment2){
                        navController.navigate(R.id.profileFragment2);
                    }
                }


                if(WelcomeActivity.guest==false){
                    if (item.getItemId() == R.id.searchFragment2){
                        navController.navigate(R.id.searchFragment2);
                    }
                    if (item.getItemId() == R.id.homeFragment2){
                        navController.navigate(R.id.homeFragment2);
                    }
                    if (item.getItemId() == R.id.favouriteFragment2 ||
                            item.getItemId() == R.id.weaklyPlanFragment ||
                            item.getItemId() == R.id.profileFragment2) {
                        showDialog();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Signup")
                .setMessage("Do you want to signup?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WelcomeActivity.guest=true;
                        Intent intent = new Intent(HomeActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}
