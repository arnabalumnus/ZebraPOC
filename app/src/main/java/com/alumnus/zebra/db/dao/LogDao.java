package com.alumnus.zebra.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alumnus.zebra.db.entity.LogEntity;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM event_log")
    List<LogEntity> getAll();

    @Insert
    void insert(LogEntity... logs);

    @Query("Select count(*) from log;")
    long getCount();
}