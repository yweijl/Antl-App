package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.EventDate;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface EventDateDao {

    @Query("SELECT * FROM event_dates")
    LiveData<List<EventDate>> getAllEventDates();

    @Insert
    void insert(EventDate EventDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EventDate> eventDates);

    @Delete
    void delete(EventDate eventDate);

    @Query("Delete From event_dates")
    void deleteAll();

    @Query("UPDATE event_dates SET event_date = :dateTime WHERE event_id = :eventId ")
    void syncEventDates(int eventId, Date dateTime);
}