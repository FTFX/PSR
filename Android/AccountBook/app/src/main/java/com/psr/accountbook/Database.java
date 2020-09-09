package com.psr.accountbook;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Item.class, User.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract ItemDao getItemDao();
    public abstract UserDao getUserDao();

    // Singleton
    private static volatile Database INSTANCE;
    static synchronized Database getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "database").build();
        }
        return INSTANCE;
    }
}
