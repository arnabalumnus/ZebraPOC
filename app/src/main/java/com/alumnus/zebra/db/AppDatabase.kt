package com.alumnus.zebra.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alumnus.zebra.db.dao.AccLogDao;
import com.alumnus.zebra.db.dao.CsvFileLogDao;
import com.alumnus.zebra.db.entity.AccLogEntity;
import com.alumnus.zebra.db.entity.CsvFileLogEntity;

@Database(entities = {AccLogEntity.class, CsvFileLogEntity.class}, views = {}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AccLogDao accLogDao();

    public abstract CsvFileLogDao csvFileLogDao();
}