package com.example.loginformjava;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userData")
public class UserData {

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "emailId")
    public String emailId;
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;
    @ColumnInfo(name = "firstName")
    public String firstName;
    @ColumnInfo(name = "lastName")
    public String lastName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
