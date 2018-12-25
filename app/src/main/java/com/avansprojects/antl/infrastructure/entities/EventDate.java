package com.avansprojects.antl.infrastructure.entities;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Event.class,
        parentColumns = "id",
        childColumns = "event_id"),
        indices = {@Index("event_id")}
)

public class EventDate {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "date_time")
    public Date dateTime;

    @ColumnInfo(name = "event_id")
    public int eventId;
}
