package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = ("friends"), indices = {@Index(value = {"web_server_id"},
        unique = true)})
public class Friend {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "web_server_id")
    public String webServerId;

    public Friend(int id){
        this.id = id;
    }

    public Friend(Friend friend){
        this.id = friend.id;
        this.userName = friend.userName;
        this.webServerId = friend.webServerId;
    }

    public String getUserName() {
        return userName;
    }

    public String getWebServerId() {
        return webServerId;
    }
}
