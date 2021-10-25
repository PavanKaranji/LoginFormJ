package com.example.loginformjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void addUser(UserData userData);

    @Query("select * from userData")
    UserData[] getUserData();

    @Query("select * from userData")
    List<UserData> getAllUserData();

    @Update
    void updateUserData(UserData userData);
}
