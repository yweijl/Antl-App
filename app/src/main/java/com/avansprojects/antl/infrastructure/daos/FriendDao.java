package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.Friend;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FriendDao {
    @Query("SELECT * FROM friends")
    LiveData<List<Friend>> getAll();

    @Query("SELECT * FROM friends WHERE id IN (:contactIds)")
    List<Friend> loadAllByIds(int[] contactIds);

    @Insert
    void insert(Friend friend);

    @Query("Delete From friends")
    void deleteAll();

    @Query("Delete From friends Where user_name is :username")
    void delete(String username);
}