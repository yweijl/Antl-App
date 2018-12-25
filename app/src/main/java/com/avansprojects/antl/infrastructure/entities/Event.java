package com.avansprojects.antl.infrastructure.entities;


import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "main_date_time")
    private Date mainDateTime;
    @ColumnInfo(name = "location")
    private String location;

    public Event(String name, Date mainDateTime, String location) {
        this.name = name;
        this.mainDateTime = mainDateTime;
        this.location = location;
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
}
