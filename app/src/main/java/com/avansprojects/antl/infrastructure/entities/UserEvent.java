package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(primaryKeys= {"user_id", "event_date_id"},
        indices = {@Index("user_id"), @Index("event_date_id")},
        foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
        @ForeignKey(entity = EventDate.class,
                parentColumns = "id",
                childColumns = "event_date_id")})

public class UserEvent {
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "event_date_id")
    public int eventDateId;
    @ColumnInfo(name = "availability")
    public String availability;
}
