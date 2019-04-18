package com.example.ratemyfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMealScreen extends AppCompatActivity {

    private EditText Protien;
    private EditText Side1;
    private EditText Side2;
    private EditText Servings;
    private EditText Address;
    private EditText City;
    private EditText State;
    private EditText Zip;
    private String MyDate;
    private Time MyTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_screen);

        final Calendar myCalendar = Calendar.getInstance();

        Protien = (EditText) findViewById(R.id.etProtein);
        Side1 = (EditText) findViewById(R.id.etSide1);
        Side2 = (EditText) findViewById(R.id.etSide2);
        Servings = (EditText) findViewById(R.id.etServings);
        Address = (EditText) findViewById(R.id.etAddress);
        City = (EditText) findViewById(R.id.etCity);
        State = (EditText) findViewById(R.id.etState);
        Zip = (EditText) findViewById(R.id.etZip);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MyDate = sdf.format(new Date());


    }
}
