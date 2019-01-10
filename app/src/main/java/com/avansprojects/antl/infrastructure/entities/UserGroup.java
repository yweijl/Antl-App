package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "user_groups", primaryKeys= {"user_id", "group_id"},
        indices = {@Index("user_id"), @Index("group_id")},
        foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
        @ForeignKey(entity = Group.class,
                parentColumns = "id",
                childColumns = "group_id")}
)

public class UserGroup {
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "group_id")
    public int groupId;
}
