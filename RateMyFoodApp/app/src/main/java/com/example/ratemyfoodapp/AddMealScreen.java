package com.example.ratemyfoodapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMealScreen extends AppCompatActivity {

    private EditText Protein;
    private EditText Side1;
    private EditText Side2;
    private EditText Servings;
    private EditText Address;
    private EditText City;
    private EditText State;
    private EditText Zip;
    private Button Submit;
    private TextView MyDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView MyTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private FirebaseAuth meAddlAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String User;
    private static final String TAG = "ProfileActivity";

    DatabaseReference mealDatabaseMembers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_screen);

        meAddlAuth = FirebaseAuth.getInstance();
        mealDatabaseMembers = FirebaseDatabase.getInstance().getReference("meals");
        Protein = (EditText) findViewById(R.id.etProtein);
        Side1 = (EditText) findViewById(R.id.etSide1);
        Side2 = (EditText) findViewById(R.id.etSide2);
        Servings = (EditText) findViewById(R.id.etServings);
        Address = (EditText) findViewById(R.id.etAddress);
        City = (EditText) findViewById(R.id.etCity);
        State = (EditText) findViewById(R.id.etState);
        Zip = (EditText) findViewById(R.id.etZip);
        MyDate = (TextView) findViewById(R.id.tvDate);
        MyTime = (TextView) findViewById(R.id.tvTime);
        Submit = (Button) findViewById(R.id.btnSubmit);

        if(meAddlAuth.getCurrentUser() != null) {
            User = meAddlAuth.getCurrentUser().getUid();
        }


        Log.d("CREATION", "DO I GET HERE 1!!");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerMeal();
            }
        });

        MyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddMealScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        MyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        AddMealScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour, minute, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                MyDate.setText(date);
            }
        };

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                MyTime.setText(time);
            }
        };
    }

    private void registerMeal() {
        String protein = Protein.getText().toString();
        String dinnerSide1 = Side1.getText().toString();
        String dinnerSide2 = Side2.getText().toString();
        String servings = Servings.getText().toString();
        String street = Address.getText().toString();
        String cty = City.getText().toString();
        String st = State.getText().toString();
        String zipcode = Zip.getText().toString();
        String time = MyTime.getText().toString();
        String date = MyDate.getText().toString();
        String usr = User;
        Log.d("CREATION", usr);

        if ((!TextUtils.isEmpty(protein)) || (!TextUtils.isEmpty(dinnerSide1)) || (!TextUtils.isEmpty(street))) {
            //Meal meal = new Meal(protein, dinnerSide1, dinnerSide2, servings, street, cty, st, zipcode, time, date);

            String id = mealDatabaseMembers.push().getKey();

            mealDatabaseMembers.child(id).child("protein").setValue(protein);
            mealDatabaseMembers.child(id).child("side 1").setValue(dinnerSide1);
            mealDatabaseMembers.child(id).child("side 2").setValue(dinnerSide2);
            mealDatabaseMembers.child(id).child("servings").setValue(servings);
            mealDatabaseMembers.child(id).child("street").setValue(street);
            mealDatabaseMembers.child(id).child("city").setValue(cty);
            mealDatabaseMembers.child(id).child("state").setValue(st);
            mealDatabaseMembers.child(id).child("zip").setValue(zipcode);
            mealDatabaseMembers.child(id).child("time").setValue(time);
            mealDatabaseMembers.child(id).child("date").setValue(date);

            Log.d("CREATION", "DO I MAKE IT IN HERE??");

            Toast.makeText(this, "New Meal Added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddMealScreen.this, MainScreen.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "You need to enter a protein or side and an address", Toast.LENGTH_LONG).show();
        }
    }
}



