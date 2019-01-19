package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = ("users"))
public class User {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "phone_number")
    public String phoneNumber;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "external_Id")
    public String externalId;

    public User(int id){
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
