package com.avansprojects.antl.infrastructure.entities;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "user_events", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
        @ForeignKey(entity = Event.class,
                parentColumns = "id",
                childColumns = "event_id")},
        indices = {@Index("user_id"), @Index("event_id")})

public class    UserEvent {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "event_id")
    private int eventId;
    @ColumnInfo(name = "event_date")
    private Date eventDate;
    @TypeConverters(AvailabilityConverter.class)
    @ColumnInfo(name = "availability")
    private Availability availability;

    public UserEvent(int userId, int eventId, Date eventDate, Availability availability) {
        this.userId = userId;
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
