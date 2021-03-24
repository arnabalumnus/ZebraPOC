package com.alumnus.zebra.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.alumnus.zebra.db.entity.AccLogEntity;

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

    @Query("Delete from accelerometer_log where ts <:time_stamp")
    void deleteAll(long time_stamp);

    @Query("Select MAX(TS), MIN(TS) from accelerometer_log;")
    long[] getLastRecordTime();

    //@Query("SELECT DISTINCT TS, count(*) FROM accelerometer_log")
    //List<String> selectDistinctRecord();

    //@Query("SELECT TS, count(*) FROM accelerometer_log group by TS")
    //List<String> selectDistinctRecord();
}