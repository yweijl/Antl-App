package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = ("relationships"),
        foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id_one"),
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id_two")},
        indices = {@Index(name = "one_two", value = {"user_id_one", "user_id_two"},
        unique = true)})
public class Relationship {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_id_one")
    private int userIdOne;
    @ColumnInfo(name = "user_id_two")
    private int userIdTwo;

    public Relationship(int userIdOne, int userIdTwo) {
        this.userIdOne = userIdOne;
        this.userIdTwo = userIdTwo;
    }


    public int getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne(int userIdOne) {
        this.userIdOne = userIdOne;
    }

    public int getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo(int userIdTwo) {
        this.userIdTwo = userIdTwo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
