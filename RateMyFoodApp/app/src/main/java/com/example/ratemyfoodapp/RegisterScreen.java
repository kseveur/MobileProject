package com.example.ratemyfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Login;
    private EditText Password;
    private EditText PhoneNumber;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        Login = (EditText) findViewById(R.id.etNewLogin);;
        Password = (EditText) findViewById(R.id.etPassword);
        PhoneNumber = (EditText) findViewById(R.id.etPhone);
        Submit = (Button) findViewById(R.id.btnRegister);

    }
}
