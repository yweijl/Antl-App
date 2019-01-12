package com.avansprojects.antl.infrastructure.entities;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_dates", foreignKeys = {
        @ForeignKey(entity = Event.class,
                parentColumns = "id",
                childColumns = "event_id")},
        indices = {@Index("event_id")})

public class EventDate {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "event_id")
    private int eventId;
    @ColumnInfo(name = "event_date")
    private Date eventDate;
}
