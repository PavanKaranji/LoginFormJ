package com.example.loginformjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {

    UserDataBase userDataBase;
    ImageButton edit1,edit2,edit3,edit4;
    EditText editEmail,editPhNum,editFName,editLName;
    Button saveChanges,showAllUsers;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z.\\s]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        userDataBase = UserDataBase.getUserDataBase(this);

        new Thread(this::getUserData).start();
//        getUserData();

        edit1 = findViewById(R.id.editEmail);
        edit2 = findViewById(R.id.editPhoneNumber);
        edit3 = findViewById(R.id.editFirstName);
        edit4 = findViewById(R.id.editLastName);

        editEmail = findViewById(R.id.emailId);
        editPhNum = findViewById(R.id.phoneNumber);
        editFName = findViewById(R.id.firstName);
        editLName = findViewById(R.id.lastName);

        saveChanges = findViewById(R.id.save);
        showAllUsers = findViewById(R.id.showAllUsers);

        edit1.setOnClickListener(view -> {
            editEmail.setEnabled(true);
            editEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            editEmail.setFocusable(true);
            enableSave();
        });
        edit2.setOnClickListener(view -> {
            editPhNum.setEnabled(true);
            editPhNum.setInputType(InputType.TYPE_CLASS_NUMBER);
            editPhNum.setFocusable(true);
            enableSave();
        });
        edit3.setOnClickListener(view -> {
            editFName.setEnabled(true);
            editFName.setInputType(InputType.TYPE_CLASS_TEXT);
            editFName.setFocusable(true);
            enableSave();
        });
        edit4.setOnClickListener(view -> {
            editLName.setEnabled(true);
            editLName.setInputType(InputType.TYPE_CLASS_TEXT);
            editLName.setFocusable(true);
            enableSave();
        });

        saveChanges.setOnClickListener(view -> {
            String mailID = editEmail.getText().toString();
            String phoneNum = editPhNum.getText().toString();
            String firstName = editFName.getText().toString();
            String lastName = editLName.getText().toString();

            if(mailID.equals("") ){
                toast("Please fill Email Id");
                editEmail.setError("Please fill Email Id");
            } else if(phoneNum.equals("") ){
                toast("Please fill Phone Number");
                editPhNum.setError("Please fill Phone Number");
            }else if(firstName.equals("") ){
                toast("Please fill First Name");
                editFName.setError("Please fill First Name");
            }else if(lastName.equals("")){
                toast("Please fill Last Name");
                editLName.setError("Please fill Last Name");
            }else{
                validate(mailID,phoneNum,firstName,lastName);
            }

        });

        showAllUsers.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeScreen.this,AllUserData.class);
            startActivity(intent);
        });
    }

    public void validate(String mailId, String phoneNumber, String firstName, String lastName){
        if(!mailId.matches(emailPattern)){
            editEmail.setError("Invalid E-mailId");
            toast("Invalid E-mailId");
        }else if(phoneNumber.length()<10){
            editPhNum.setError("Enter 10 digit phone Number");
            toast("Enter 10 digit phone Number");
        }else if(!firstName.matches(namePattern)){
            editFName.setError("Invalid First Name");
            toast("Invalid First Name");
        }else if(!lastName.matches(namePattern)){
            editLName.setError("Invalid Last Name");
            toast("Invalid Last Name");
        }else{
            updateUser(mailId,phoneNumber,firstName,lastName);
        }
    }

    public void toast(String text){
        Toast.makeText(WelcomeScreen.this,text,Toast.LENGTH_LONG).show();
    }

    public void enableSave(){
        saveChanges.setEnabled(true);
    }

    public void updateUser(String mailId,String phoneNumber,String firstName,String lastName){
        new Thread(() -> {
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
            editFName.setText(user.firstName);
            editLName.setText(user.lastName);
        }
    }

    public void refreshActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
