package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "user_id"),
        indices = {@Index("user_id")})

public class FriendShip {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "user_id")
    public int userId;
}
