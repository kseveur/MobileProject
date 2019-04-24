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
    private FirebaseDatabase mFireBaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mynewRef;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    private String userID;
    private ListView mListView;
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText PhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        mAuth = FirebaseAuth.getInstance();
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mynewRef = mFireBaseDatabase.getReference("members");
        user = mAuth.getCurrentUser();
        Submit = (Button) findViewById(R.id.btnSubmit);
        progressDialog = new ProgressDialog(this);
        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        Email = (EditText) findViewById(R.id.etEmail);
        PhoneNumber = (EditText) findViewById(R.id.etPhone);





        userID = user.getUid();

        Log.d("CREATION", "DO I GT HERE!!!!!!");

        mynewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("CREATION", "DO I GT HERE 11111!!!!!!");

                String fname = dataSnapshot.child("members").child(userID).child("Fname").getValue(String.class);
                String lname = dataSnapshot.child("members").child(userID).child("Lname").getValue(String.class);
                String email = dataSnapshot.child("members").child(userID).child("email").getValue(String.class);
                String phone = dataSnapshot.child("members").child(userID).child("phone").getValue(String.class);
                Log.d("CREATION", "DO I GT HERE 22222!!!!!!");


                FirstName.setText(fname, TextView.BufferType.EDITABLE);
                LastName.setText(lname, TextView.BufferType.EDITABLE);
                Email.setText(email, TextView.BufferType.EDITABLE);
                PhoneNumber.setText(phone, TextView.BufferType.EDITABLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })   ;




        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
            }
        });










        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged : signed in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "OnAuthStateChanged: signed-out");
                    toastMessage("Successfully signed out");
                }
            }
        };  */


        /*mynewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    /*   Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProfileIntent = new Intent(ProfileScreen.this, MainScreen.class);
                startActivity(ProfileIntent);
            }
        });*/
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


    }

    /*private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Member memberInfo = new Member();
            memberInfo.setFirst(ds.child(userID).getValue(Member.class).getFname());  // set the first name
            memberInfo.setLast(ds.child(userID).getValue(Member.class).getLname());  // set the first name
            memberInfo.setTheLogin(ds.child(userID).getValue(Member.class).getLoginId());  // set the first name
            memberInfo.setPhoneNum(ds.child(userID).getValue(Member.class).getPhone());  // set the first name


            ArrayList<String> array = new ArrayList<>();
            array.add(memberInfo.getFname());
            array.add(memberInfo.getLname());
            array.add(memberInfo.getLoginId());
            array.add(memberInfo.getPhone());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mListView.setAdapter(adapter);
        }
    }*/


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener((mAuthListener));
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


