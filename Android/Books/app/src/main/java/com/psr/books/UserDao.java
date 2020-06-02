package com.psr.books;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User... users);
    @Delete
    void delUser(User... users);
    @Update
    void modUser(User... users);
    @Query("select count(*) from user where username=:username and password=:password")
    int getUser(String username, String password);
    @Query("select count(*) from user where username=:username")
    int valUser(String username);
    @Query("select * from user")
    List<User> getAllUsers();
}