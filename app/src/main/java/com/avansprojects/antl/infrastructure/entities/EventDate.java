package com.avansprojects.antl.infrastructure.entities;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_dates", foreignKeys = {
        @ForeignKey(entity = Event.class,
                parentColumns = "id",
                childColumns = "event_id")},
        indices = {@Index("event_id")})

public class EventDate implements Comparable<EventDate> {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "event_id")
    private int eventId;
    @ColumnInfo(name = "event_date")
    private Date eventDate;

    public EventDate(int id, int eventId, Date eventDate) {
        this.id = id;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }

    @Ignore
    public EventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public int compareTo(EventDate o) {
        return getEventDate().compareTo(o.getEventDate());
    }
}
