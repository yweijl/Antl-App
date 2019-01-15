package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE id Is (:userId)")
    LiveData<User> loadById(int userId);

    @Insert
    void insert(User user);

    @Query("Delete From users")
    void deleteAll();
}