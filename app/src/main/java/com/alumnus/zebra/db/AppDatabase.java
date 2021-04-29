package com.alumnus.zebra.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alumnus.zebra.db.dao.AccLogDao;
import com.alumnus.zebra.db.dao.CsvFileLogDao;
import com.alumnus.zebra.db.dao.EventLogDao;
import com.alumnus.zebra.db.dao.LogDao;
import com.alumnus.zebra.db.entity.AccLogEntity;
import com.alumnus.zebra.db.entity.CsvFileLogEntity;
import com.alumnus.zebra.db.entity.EventLogEntity;
import com.alumnus.zebra.db.entity.LogEntity;

@Database(entities = {EventLogEntity.class, AccLogEntity.class, LogEntity.class, CsvFileLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventLogDao eventLogDao();

    public abstract AccLogDao accLogDao();

    public abstract LogDao logDao();

    public abstract CsvFileLogDao csvFileLogDao();
}