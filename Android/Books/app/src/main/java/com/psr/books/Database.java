package com.psr.books;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UserDao getDao();

    // Singleton
    private static volatile Database INSTANCE;
    static synchronized Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "database").build();
        }
        return INSTANCE;
    }
}
