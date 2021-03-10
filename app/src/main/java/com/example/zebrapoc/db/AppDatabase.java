package com.example.zebrapoc.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.zebrapoc.db.dao.AccLogDao;
import com.example.zebrapoc.db.dao.EventLogDao;
import com.example.zebrapoc.db.entity.AccLogEntity;
import com.example.zebrapoc.db.entity.EventLogEntity;

@Database(entities = {EventLogEntity.class, AccLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventLogDao eventLogDao();

    public abstract AccLogDao accLogDao();
}