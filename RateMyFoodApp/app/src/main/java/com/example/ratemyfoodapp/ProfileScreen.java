package com.example.ratemyfoodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileScreen extends AppCompatActivity {

    private Button Submit;
    private static final String TAG = "ProfileActivity";
    private DatabaseReference myRef;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    private String userID;
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText PhoneNumber;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFireBaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        Email = (EditText) findViewById(R.id.etEmail);
        PhoneNumber = (EditText) findViewById(R.id.etPhone);
        Submit = (Button) findViewById(R.id.btnSubmit);
        mAuth = FirebaseAuth.getInstance();
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();


        myRef = mFireBaseDatabase.getReference("members");
        Log.d("CREATION", myRef.toString());
        Log.d("CREATION", myRef.toString());


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Member member = dataSnapshot.getValue(Member.class);

                if(member.getFname() != null) FirstName.setText(member.getFname());
                if(member.getLname() != null) LastName.setText(member.getLname());
                if(member.getLoginId() != null) Email.setText(member.getLoginId());
                if(member.getPhone() != null) PhoneNumber.setText(member.getPhone());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                toastMessage("Database error!!");
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
                toastMessage("Profile successfully updated!");
            }
        });


    }



    private void saveUserInfo() {
        DatabaseReference databaseMembers = FirebaseDatabase.getInstance().getReference("members");
        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        Email = (EditText) findViewById(R.id.etEmail);
        PhoneNumber = (EditText) findViewById(R.id.etPhone);
        String id = databaseMembers.push().getKey();



        if(FirstName != null) {
            String fname = FirstName.getText().toString().trim();
            databaseMembers.child(id).child("Fname").setValue(fname);
        }
        if(LastName != null) {
            String lname = LastName.getText().toString().trim();
            databaseMembers.child(id).child("Lnams").setValue(lname);
        }

        if(Email != null) {
            String email = Email.getText().toString().trim();
            databaseMembers.child(id).child("Email").setValue(email);
        }
        if(PhoneNumber != null) {
            String phone = PhoneNumber.getText().toString();
            databaseMembers.child(id).child("Phone").setValue(phone);
        }

        Intent intent = new Intent(ProfileScreen.this, MainScreen.class);
        startActivity(intent);


    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Member memberInfo = new Member();
            memberInfo.setFirst(ds.child("members").child(userID).getValue(Member.class).getFname());  // set the first name
            memberInfo.setLast(ds.child("members").child(userID).getValue(Member.class).getLname());  // set the first name
            memberInfo.setTheLogin(ds.child("members").child(userID).getValue(Member.class).getLoginId());  // set the first name
            memberInfo.setPhoneNum(ds.child("members").child(userID).getValue(Member.class).getPhone());  // set the first name

            Log.d(TAG, "showData: firstname: " + memberInfo.getFname());
            Log.d(TAG, "showData: lastname: " + memberInfo.getLname());
            Log.d(TAG, "showData: email: " + memberInfo.getLoginId());
            Log.d(TAG, "showData: phone: " + memberInfo.getPhone());

            FirstName.setText(memberInfo.getFname());
            LastName.setText((memberInfo.getLname()));
            Email.setText(memberInfo.getLoginId());
            PhoneNumber.setText(memberInfo.getPhone());
        }
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


