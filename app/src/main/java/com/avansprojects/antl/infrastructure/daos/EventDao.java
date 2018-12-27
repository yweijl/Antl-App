package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.Event;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EventDao {

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Insert
    void insert(Event event);

    @Insert
    void insertAll(Event... events);

    @Delete
    void delete(Event event);

    @Query("Delete From events")
    void deleteAll();
}