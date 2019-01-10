package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = ("users"))
public class User {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "phone_number")
    public long phoneNumber;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "web_server_id")
    public long webServerId;
    @ColumnInfo(name = "gender")
    public String gender;

    public User(int id){
        this.id = id;
    }
}
