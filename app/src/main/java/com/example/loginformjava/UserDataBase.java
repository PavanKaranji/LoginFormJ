package com.example.loginformjava;

import android.content.Context;
import android.os.Environment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;

@Database(entities = UserData.class,version = 1)
public abstract class UserDataBase extends RoomDatabase {
    private static UserDataBase userDataBase;
    public abstract UserDAO userDAO();
    private static final String dbName = "user";

    public static synchronized UserDataBase getUserDataBase(Context context){
        if(userDataBase == null){
//            String fileName = "userLoginDB";
//            String extenalDir  = getExternalStorageDirectory();
//            String finalDbPath = extenalDir+ "/" + fileName;
//
//            File directory = new File(finalDbPath);
//            if(!directory.exists()) {
//                directory.mkdirs();
//            }

            userDataBase = Room.databaseBuilder(context,UserDataBase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDataBase;
    }

    public static String getExternalStorageDirectory() {

        String filePath = Environment.getExternalStorageDirectory()+ File.separator+"LoginApplication";

        File directory = new File(filePath);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        return  filePath;
    }

}
