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
    @ColumnInfo(name = "external_id")
    private String ExternalId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "hash")
    private int hash;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "main_date_time")
    private Date mainDateTime;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "picture_path")
    private String PicturePath;
    @ColumnInfo(name = "is_owner")
    private boolean isOwner;



    @Ignore
    public Event() {
    }

    public Event(String name, String ExternalId, Date mainDateTime, String location, String description, String PicturePath, boolean isOwner, int hash) {
        this.name = name;
        this.mainDateTime = mainDateTime;
        this.location = location;
        this.PicturePath = PicturePath;
        this.description = description;
        this.ExternalId = ExternalId;
        this.isOwner = isOwner;
        this.hash = hash;
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

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        this.PicturePath = picturePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalId() {
        return ExternalId;
    }

    public void setExternalId(String externalId) {
        ExternalId = externalId;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public int compareTo(Event o) {
        return getMainDateTime().compareTo(o.getMainDateTime());
    }
}
