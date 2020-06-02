package com.psr.accountbook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    public void addItem(Item... items);
    @Delete
    public void delItem(Item... items);
    @Update
    public void modItem(Item... items);
    @Query("SELECT * FROM item WHERE name LIKE :name")
    public List<Item> getItem(String... name);
    @Query("SELECT * FROM item")
    public LiveData<List<Item>> getAllItems();
}
