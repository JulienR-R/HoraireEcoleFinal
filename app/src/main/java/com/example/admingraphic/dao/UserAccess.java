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
    void insertUsers(ArrayList<User> users);
    @Query("SELECT * FROM User")
    List<User> getUsers();
    @Query("SELECT * FROM User WHERE _id = :id")
    User getUser(int id);
    @Query("SELECT * FROM User WHERE userID = :userId")
    User getUser(String userId);
    @Update()
    int updateUser(User user);
    @Delete
    int deleteUser(User user);
}
