package com.example.loginformjava;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AllUserData extends AppCompatActivity {

    ListView myList;
    UserDataBase userDataBase;
    CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        userDataBase = UserDataBase.getUserDataBase(this);
        myList = findViewById(R.id.myList);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getAllUserData();
            }
        }).start();

    }

    public void getAllUserData(){
        List<UserData> users = userDataBase.userDAO().getAllUserData();
        customeAdapter = new CustomeAdapter(AllUserData.this,0,users);
        myList.setAdapter(customeAdapter);
//        myList.setDivider(null);
        myList.setDividerHeight(1);
    }
}
