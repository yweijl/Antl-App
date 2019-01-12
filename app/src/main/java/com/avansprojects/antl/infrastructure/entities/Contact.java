package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = ("contacts"), indices = {@Index(value = {"web_server_id"},
        unique = true)})
public class Contact {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "web_server_id")
    public String webServerId;

    public Contact(int id){
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
}
