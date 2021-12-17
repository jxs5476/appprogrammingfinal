package com.jxs5476.showtracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
    public interface UserDao {

        @Query("SELECT * FROM User WHERE id = :id")
        public  User getUserbyId(String id);

        @Query("SELECT * FROM User ORDER BY username")
        public List<User> getUsers();

        @Query("SELECT * FROM User WHERE username = :name")
        public User getUser(String name);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public void insertUser(User User);

        @Update
        public void updateUser(User User);

        @Delete
        public void deleteUser(User User);
    }

