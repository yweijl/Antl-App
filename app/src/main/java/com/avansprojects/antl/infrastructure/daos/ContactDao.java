package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.Contact;
import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contacts WHERE id IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Insert
    void insert(Contact contact);

    @Query("Delete From contacts")
    void deleteAll();
}