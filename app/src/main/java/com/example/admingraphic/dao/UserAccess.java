package com.example.admingraphic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.admingraphic.database.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);
    @Query("SELECT * FROM users WHERE isAdmin = 0")
    List<User> getUsers();
    @Query("SELECT * FROM users WHERE _id = :id")
    User getUser(int id);
    @Query("SELECT * FROM users WHERE userID = :userId AND pwd = :pwd")
    User getUser(String userId,String pwd);
    @Update()
    int updateUser(User user1);
    @Delete
    int deleteUser(User user);
}
