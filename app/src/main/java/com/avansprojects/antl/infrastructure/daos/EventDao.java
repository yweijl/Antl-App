package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventSyncDto;
import com.avansprojects.antl.infrastructure.entities.Event;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EventDao {

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Insert
    void insert(Event event);

    @Insert
    long insertRetrieveId(Event event);

    @Insert
    void insertAll(Event... events);

    @Query("UPDATE events SET external_id = :externalId, hash = :hash where id = :id")
    void updateHashAndExternalID(String externalId, int hash , int id);

    @Query("SELECT external_Id, hash FROM events")
    List<EventSyncDto> getEventSyncList();

    @Delete
    void delete(Event event);

    @Query("UPDATE events SET main_date_time = :mainDate, location = :location, picture_path = :picturePath, description=:description, is_Owner=:isOwner, hash=:hash where external_id = :externalId")
    void updateByExternalId(Date mainDate, String location, String picturePath, String description, boolean isOwner, String hash, String externalId);

    @Query("SELECT id FROM events WHERE external_id = :externalId")
    int getIdFromEvent(String externalId);

    @Query("Delete From events")
    void deleteAll();
}


