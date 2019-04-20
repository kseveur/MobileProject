package com.example.ratemyfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HostScreen extends AppCompatActivity {

    private Button AddMeal;
    private Button TakePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_screen);


        AddMeal = (Button) findViewById(R.id.btnAddMeal);
        TakePic = (Button) findViewById(R.id.btnTakePic);

        AddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hostIntent = new Intent(HostScreen.this, AddMealScreen.class);
                startActivity(hostIntent);
            }
        });

        /*TakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GuestIntent = new Intent(MainScreen.this, TakePic.class);
                startActivity(GuestIntent);
            }
        });*/
    }
}
