package com.example.zebrapoc.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EventLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventLogDao eventLogDao();
}