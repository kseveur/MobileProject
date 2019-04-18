package com.example.ratemyfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileScreen extends AppCompatActivity {

    private Button Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        Settings = (Button) findViewById(R.id.btnSetting);


       /* Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hostIntent = new Intent(ProfileScreen.this, SettingScreen.class);
                startActivity(hostIntent);
            }
        });*/
    }
}
