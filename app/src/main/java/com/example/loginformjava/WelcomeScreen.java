package com.example.loginformjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {

    UserDataBase userDataBase;
    ImageButton edit1,edit2,edit3,edit4;
    EditText editEmail,editPhNum,editFname,editLname;
    Button saveChanges,showAllUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        userDataBase = UserDataBase.getUserDataBase(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getUserData();
            }
        }).start();
//        getUserData();

        edit1 = findViewById(R.id.editEmail);
        edit2 = findViewById(R.id.editPhoneNumber);
        edit3 = findViewById(R.id.editFirstName);
        edit4 = findViewById(R.id.editLastName);

        editEmail = findViewById(R.id.emailId);
        editPhNum = findViewById(R.id.phoneNumber);
        editFname = findViewById(R.id.firstName);
        editLname = findViewById(R.id.lastName);

        saveChanges = findViewById(R.id.save);
        showAllUsers = findViewById(R.id.showAllUsers);

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEmail.setEnabled(true);
                editEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                editEmail.setFocusable(true);
                enableSave();
            }
        });
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPhNum.setEnabled(true);
                editPhNum.setInputType(InputType.TYPE_CLASS_NUMBER);
                editPhNum.setFocusable(true);
                enableSave();
            }
        });
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFname.setEnabled(true);
                editFname.setInputType(InputType.TYPE_CLASS_TEXT);
                editFname.setFocusable(true);
                enableSave();
            }
        });
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLname.setEnabled(true);
                editLname.setInputType(InputType.TYPE_CLASS_TEXT);
                editLname.setFocusable(true);
                enableSave();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailID = editEmail.getText().toString();
                String phoneNum = editPhNum.getText().toString();
                String firstName = editFname.getText().toString();
                String lastName = editLname.getText().toString();
                updateUser(mailID,phoneNum,firstName,lastName);


            }
        });

        showAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this,AllUserData.class);
                startActivity(intent);
            }
        });
    }

    public void enableSave(){
        saveChanges.setEnabled(true);
    }

    public void updateUser(String mailId,String phoneNumber,String firstName,String lastName){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData[] userDatas = userDataBase.userDAO().getUserData();

                int lastIndex = userDatas.length - 1 ;
                UserData user = userDatas[lastIndex];

                UserData userData = new UserData();
                userData.setUserId(user.getUserId());
                userData.setEmailId(mailId);
                userData.setPhoneNumber(phoneNumber);
                userData.setFirstName(firstName);
                userData.setLastName(lastName);
                userDataBase.userDAO().updateUserData(userData);

//                getUserData();
                refreshActivity();
            }
        }).start();
    }

    public void getUserData(){
        UserData[] userDatas = userDataBase.userDAO().getUserData();
        if(userDatas.length < 1){
            Toast.makeText(WelcomeScreen.this,"Failed to get User data",Toast.LENGTH_LONG).show();
        }else{
            int lastIndex = userDatas.length - 1 ;
            UserData user = userDatas[lastIndex];

            editEmail.setText(user.emailId);
            editPhNum.setText(user.phoneNumber);
            editFname.setText(user.firstName);
            editLname.setText(user.lastName);
        }
    }

    public void refreshActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
