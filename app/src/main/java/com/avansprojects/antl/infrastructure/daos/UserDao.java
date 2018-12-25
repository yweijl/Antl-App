package com.avansprojects.antl.infrastructure.daos;

import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);
}