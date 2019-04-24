package com.example.ratemyfoodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Submit;
    private ProgressDialog progressDialog;
    private FirebaseAuth fireBaseAuth;
    private String dbUserID;

    DatabaseReference databaseMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        databaseMembers = FirebaseDatabase.getInstance().getReference("members");
        fireBaseAuth = FirebaseAuth.getInstance();
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etNewPassword);
        Submit = (Button) findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerMember();
            }
        });

    }

    private void registerMember() {
        String email = Email.getText().toString().trim();
        String psswd = Password.getText().toString();



        if((!TextUtils.isEmpty(email)) && !TextUtils.isEmpty(psswd)) {
            progressDialog.setMessage(("Registering User......"));
            progressDialog.show();

            fireBaseAuth.createUserWithEmailAndPassword(email, psswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                toastMessage("New Member Added");
                                Intent intent = new Intent(RegisterScreen.this, MainScreen.class);
                                startActivity(intent);
                            }
                        }
                    });



           /* String id = databaseMembers.push().getKey();
            //Member member = new Member(Fname, Lname, LoginID, Phone);

            databaseMembers.child(id).child("Login").setValue(email);
            databaseMembers.child(id).child("Password").setValue(psswd);
            databaseMembers.child(id).child("Fname").setValue(fname);
            databaseMembers.child(id).child("Lnams").setValue(lname);
            databaseMembers.child(id).child("Phone").setValue(phone);



            Log.d("CREATION", Fname);
=======
        String Fname = FirstName.getText().toString().trim();
        String Lname = LastName.getText().toString().trim();
        String LoginID = Login.getText().toString().trim();
        String Phone = PhoneNumber.getText().toString();

        if(!TextUtils.isEmpty(LoginID)) {
            String id = databaseMembers.push().getKey();
            Member member = new Member(Fname, Lname, LoginID, Phone);
>>>>>>> 77a522bc99485c0ac3cf812b62b05671d6091de7



            Toast.makeText(this, "New Member Added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
            startActivity(intent); */

        } else {
            Toast.makeText(this, "You need to enter a login and password", Toast.LENGTH_LONG).show();
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
