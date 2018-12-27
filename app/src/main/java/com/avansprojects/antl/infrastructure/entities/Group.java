package com.avansprojects.antl.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Group {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String Name;
}
