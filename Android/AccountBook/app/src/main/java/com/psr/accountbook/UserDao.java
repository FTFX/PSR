package com.psr.accountbook;

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
    @Query("SELECT count(*) FROM user WHERE username LIKE :username AND password LIKE :password")
    int valUser(String username, String password);
    @Query("SELECT count(*) FROM user WHERE username LIKE :username")
    int valUser(String username);
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
}
