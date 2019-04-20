package com.example.ratemyfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Login;
    private EditText Password;
    private EditText PhoneNumber;
    private Button Submit;
    private String dbUserID;

    DatabaseReference databaseMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        databaseMembers = FirebaseDatabase.getInstance().getReference("members");
        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        Login = (EditText) findViewById(R.id.etNewLogin);
        Password = (EditText) findViewById(R.id.etNewPassword);
        PhoneNumber = (EditText) findViewById(R.id.etPhone);
        Submit = (Button) findViewById(R.id.btnRegister);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerMember();
            }
        });

    }

    private void registerMember() {
        String Fname = FirstName.getText().toString().trim();
        String Lname = LastName.getText().toString().trim();
        String LoginID = Login.getText().toString().trim();
        String Phone = PhoneNumber.getText().toString();
    
        if(!TextUtils.isEmpty(LoginID)) {
            String id = databaseMembers.push().getKey();
            Member member = new Member(Fname, Lname, LoginID, Phone);

            databaseMembers.child(id).setValue(member);


            Toast.makeText(this, "New Member Added", Toast.LENGTH_LONG).show();
           // Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
           // startActivity(intent);

        } else {
            Toast.makeText(this, "You need to enter a login and password", Toast.LENGTH_LONG).show();
        }
    }
}
