package com.example.loginformjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,phone,fname,lname;
    Button signIn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z.\\s]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailId);
        phone = findViewById(R.id.phoneNumber);
        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailId = email.getText().toString();
                String phoneNumber = phone.getText().toString();
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                if(mailId.equals("") || phoneNumber.equals("") || firstName.equals("") || lastName.equals("")){
                    toast("Please fill all fields");
                }else{
                    validate(mailId,phoneNumber,firstName,lastName);
                }
            }
        });
    }

    public void validate(String mailId,String phoneNumber,String firstName,String lastName){
        if(!mailId.matches(emailPattern)){
            email.setError("Invalid E-mailId");
            toast("Invalid E-mailId");
        }else if(phoneNumber.length()<10){
            phone.setError("Enter 10 digit phone Number");
            toast("Enter 10 digit phone Number");
        }else if(!firstName.matches(namePattern)){
            fname.setError("Invalid First Name");
            toast("Invalid First Name");
        }else if(!lastName.matches(namePattern)){
            lname.setError("Invalid Last Name");
            toast("Invalid Last Name");
        }else{
            toast("Sign In validation Successful");
            email.setText("");
            phone.setText("");
            fname.setText("");
            lname.setText("");
        }
    }

    public void toast(String text){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
    }
}