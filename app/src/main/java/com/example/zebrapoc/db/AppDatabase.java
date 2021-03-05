package com.example.zebrapoc.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.zebrapoc.db.dao.EventLogDao;
import com.example.zebrapoc.db.entity.EventLogEntity;

@Database(entities = {EventLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventLogDao eventLogDao();
}