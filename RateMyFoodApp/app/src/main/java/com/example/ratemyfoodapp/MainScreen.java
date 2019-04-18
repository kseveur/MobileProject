package com.example.ratemyfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {


    private Button Host;
    private Button Guest;
    private Button FoodGallery;
    private Button SearchDish;
    private Button Profiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        Host = (Button) findViewById(R.id.btnHost);
        Guest = (Button) findViewById(R.id.btnGuest);
        FoodGallery = (Button) findViewById(R.id.btnFoodGallery);
        SearchDish = (Button) findViewById(R.id.btnSearchDish);
        Profiles = (Button) findViewById(R.id.btnProfiles);

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

        FoodGallery.setOnClickListener(new View.OnClickListener() {
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
        });

        Profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProfileIntent = new Intent(MainScreen.this, ProfileScreen.class);
                startActivity(ProfileIntent);
            }
        });





    }






}
