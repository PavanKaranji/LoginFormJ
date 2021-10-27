package com.example.loginformjava;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,phone,fname,lname;
    Button signIn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z.\\s]+";
    UserDataBase userDataBase;

    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataBase = UserDataBase.getUserDataBase(getApplicationContext());

//        if (Build.VERSION.SDK_INT >= 23)
//        {
//            if (checkPermission())
//            {
//                // Code for above or equal 23 API Oriented Device
//                // Your Permission granted already .Do next code
//            } else {
//                requestPermission(); // Code for permission
//            }
//        }
//        else
//        {
//
//        }

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
                if(mailId.equals("") ){
                    toast("Please fill Email Id");
                    email.setError("Please fill Email Id");
                } else if(phoneNumber.equals("") ){
                    toast("Please fill Phone Number");
                    phone.setError("Please fill Phone Number");
                }else if(firstName.equals("") ){
                    toast("Please fill First Name");
                    fname.setError("Please fill First Name");
                }else if(lastName.equals("")){
                    toast("Please fill Last Name");
                    lname.setError("Please fill Last Name");
                }
                else{
                    validate(mailId,phoneNumber,firstName,lastName);
                }
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
            fname.setError("Invalid First Name");
            toast("Invalid First Name");
        }else if(!lastName.matches(namePattern)){
            lname.setError("Invalid Last Name");
            toast("Invalid Last Name");
        }else{
            addUserData(mailId,phoneNumber,firstName,lastName);
            email.setText("");
            phone.setText("");
            fname.setText("");
            lname.setText("");
        }
    }

    public void addUserData(String mailId,String phoneNumber,String firstName,String lastName){
        UserData userData = new UserData();
        userData.setEmailId(mailId);
        userData.setPhoneNumber(phoneNumber);
        userData.setFirstName(firstName);
        userData.setLastName(lastName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDataBase.userDAO().addUser(userData);
//                Toast.makeText(getApplicationContext(),"User Sign In Successful..",Toast.LENGTH_SHORT).show();
                goToWelcomeScreen();
            }
        }).start();

    }

    public void goToWelcomeScreen(){
        Intent intent = new Intent(MainActivity.this,WelcomeScreen.class);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
    }

//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    private void requestPermission() {
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }
}