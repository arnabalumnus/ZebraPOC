package com.example.zebrapoc.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zebrapoc.db.entity.LogEntity;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM event_log")
    List<LogEntity> getAll();

    @Insert
    void insert(LogEntity... logs);

}