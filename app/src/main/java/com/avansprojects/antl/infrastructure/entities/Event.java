package com.avansprojects.antl.infrastructure.entities;

import java.util.Date;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event implements Comparable<Event> {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "main_date_time")
    private Date mainDateTime;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "eventPicture")
    private int eventPicture;
    @ColumnInfo(name = "owner_web_server_id")
    private long eventOwnerId;

    @Ignore
    public Event() {
    }

    public Event(String name, Date mainDateTime, String location, String description, int eventPicture, long eventOwnerId) {
        this.name = name;
        this.mainDateTime = mainDateTime;
        this.location = location;
        this.eventPicture = eventPicture;
        this.description = description;
        this.eventOwnerId =  eventOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMainDateTime() {
        return mainDateTime;
    }

    public void setMainDateTime(Date mainDateTime) {
        this.mainDateTime = mainDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(int eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEventOwnerId() {
        return eventOwnerId;
    }

    public void setEventOwnerId(long eventOwnerId) {
        this.eventOwnerId = eventOwnerId;
    }

    @Override
    public int compareTo(Event o) {
        return getMainDateTime().compareTo(o.getMainDateTime());
    }
}
