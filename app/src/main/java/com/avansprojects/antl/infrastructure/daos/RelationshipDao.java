package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.Relationship;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RelationshipDao {

    @Query("SELECT * FROM relationships")
    LiveData<List<Relationship>> getAllRelationships();

    @Insert
    void insert(Relationship relationship);

    @Insert
    void insertAll(Relationship... relationships);

    @Delete
    void delete(Relationship relationship);

    @Query("Delete From relationships")
    void deleteAll();
}