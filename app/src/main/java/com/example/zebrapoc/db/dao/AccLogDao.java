package com.example.zebrapoc.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zebrapoc.db.entity.AccLogEntity;
import com.example.zebrapoc.db.entity.EventLogEntity;

import java.util.List;

@Dao
public interface AccLogDao {

    @Query("SELECT * FROM accelerometer_log")
    List<AccLogEntity> getAll();

    @Insert
    void insert(AccLogEntity... accelerometer_log);

    @Delete
    void delete(AccLogEntity accelerometer_log);

    @Query("Select count(*) from accelerometer_log;")
    long getCount();

    @Query("Delete from event_log")
    void deleteAll();
}