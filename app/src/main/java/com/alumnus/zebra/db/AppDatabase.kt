package com.alumnus.zebra.db

import androidx.room.Database
import com.alumnus.zebra.db.entity.AccLogEntity
import com.alumnus.zebra.db.entity.CsvFileLogEntity
import androidx.room.RoomDatabase
import com.alumnus.zebra.db.dao.AccLogDao
import com.alumnus.zebra.db.dao.CsvFileLogDao

@Database(entities = [AccLogEntity::class, CsvFileLogEntity::class], views = [], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accLogDao(): AccLogDao

    abstract fun csvFileLogDao(): CsvFileLogDao

}