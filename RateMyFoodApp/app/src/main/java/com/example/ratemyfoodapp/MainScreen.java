package com.example.ratemyfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends AppCompatActivity {


    private Button Host;
    private Button Guest;
    private Button FoodGallery;
    private Button SearchDish;
    private Button Profiles;
    private Button SignOut;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mAuth = FirebaseAuth.getInstance();
        Host = (Button) findViewById(R.id.btnHost);
        Guest = (Button) findViewById(R.id.btnGuest);
        FoodGallery = (Button) findViewById(R.id.btnFoodGallery);
        SearchDish = (Button) findViewById(R.id.btnSearchDish);
        Profiles = (Button) findViewById(R.id.btnProfiles);
        SignOut = (Button) findViewById(R.id.btnLogout);

        Host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hostIntent = new Intent(MainScreen.this, HostScreen.class);
                startActivity(hostIntent);
            }
        });

        Guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GuestIntent = new Intent(MainScreen.this, GuestScreen.class);
                startActivity(GuestIntent);
            }
        });

      /*  FoodGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FoodGIntent = new Intent(MainScreen.this, FoodGScreen.class);
                startActivity(FoodGIntent);
            }
        });

        SearchDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SearchDIntent = new Intent(MainScreen.this, SearchDScreen.class);
                startActivity(SearchDIntent);
            }
        });*/

        Profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(MainScreen.this, ProfileScreen.class);
                startActivity(MainIntent);
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing out!");
                Intent ProfileIntent = new Intent(MainScreen.this, LoginScreen.class);
                startActivity(ProfileIntent);
            }
        });

    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener((mAuthListener));
        }
    }*/
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}
