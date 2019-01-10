package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Insert
    void insert(User user);

    @Query("Delete From users")
    void deleteAll();
}