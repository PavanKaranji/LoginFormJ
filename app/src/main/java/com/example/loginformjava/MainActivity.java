package com.example.loginformjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,phone, fName,lName;
    Button signIn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z.\\s]+";
    UserDataBase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataBase = UserDataBase.getUserDataBase(getApplicationContext());

        String currentDBPath=getDatabasePath("user.db").getAbsolutePath();
        System.out.println("Db Path:"+currentDBPath);


        email = findViewById(R.id.emailId);
        phone = findViewById(R.id.phoneNumber);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(view -> {
            String mailId = email.getText().toString();
            String phoneNumber = phone.getText().toString();
            String firstName = fName.getText().toString();
            String lastName = lName.getText().toString();
            if(mailId.equals("") ){
                toast("Please fill Email Id");
                email.setError("Please fill Email Id");
            } else if(phoneNumber.equals("") ){
                toast("Please fill Phone Number");
                phone.setError("Please fill Phone Number");
            }else if(firstName.equals("") ){
                toast("Please fill First Name");
                fName.setError("Please fill First Name");
            }else if(lastName.equals("")){
                toast("Please fill Last Name");
                lName.setError("Please fill Last Name");
            }
            else{
                validate(mailId,phoneNumber,firstName,lastName);
            }
        });
    }

    public void validate(String mailId, String phoneNumber, String firstName, String lastName){
        if(!mailId.matches(emailPattern)){
            email.setError("Invalid E-mailId");
            toast("Invalid E-mailId");
        }else if(phoneNumber.length()<10){
            phone.setError("Enter 10 digit phone Number");
            toast("Enter 10 digit phone Number");
        }else if(!firstName.matches(namePattern)){
            fName.setError("Invalid First Name");
            toast("Invalid First Name");
        }else if(!lastName.matches(namePattern)){
            lName.setError("Invalid Last Name");
            toast("Invalid Last Name");
        }else{
            addUserData(mailId,phoneNumber,firstName,lastName);
            email.setText("");
            phone.setText("");
            fName.setText("");
            lName.setText("");
        }
    }

    public void addUserData(String mailId,String phoneNumber,String firstName,String lastName){
        UserData userData = new UserData();
        userData.setEmailId(mailId);
        userData.setPhoneNumber(phoneNumber);
        userData.setFirstName(firstName);
        userData.setLastName(lastName);
        new Thread(() -> {
            userDataBase.userDAO().addUser(userData);
//                Toast.makeText(getApplicationContext(),"User Sign In Successful..",Toast.LENGTH_SHORT).show();
            goToWelcomeScreen();
        }).start();

    }

    public void goToWelcomeScreen(){
        Intent intent = new Intent(MainActivity.this,WelcomeScreen.class);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
    }
}