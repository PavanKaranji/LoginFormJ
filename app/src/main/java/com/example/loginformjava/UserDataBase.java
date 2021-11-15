package com.example.loginformjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserData.class,version = 1)
public abstract class UserDataBase extends RoomDatabase {
    private static UserDataBase userDataBase;
    public abstract UserDAO userDAO();
    private static final String dbName = "user";

    public static synchronized UserDataBase getUserDataBase(Context context){
        if(userDataBase == null){

            userDataBase = Room.databaseBuilder(context,UserDataBase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDataBase;
    }

}
