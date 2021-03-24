package com.alumnus.zebra.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.alumnus.zebra.db.entity.EventLogEntity;

import java.util.List;

@Dao
public interface EventLogDao {

    @Query("SELECT * FROM event_log")
    List<EventLogEntity> getAll();

    @Insert
    void insert(EventLogEntity... eventLogs);

    @Delete
    void delete(EventLogEntity eventLogs);

    @Query("Select count(*) from event_log;")
    long getCount();

    @Query("Select MAX(uid) from event_log;")
    long getMax();

    @Query("Select * from event_log where uid = (Select MAX(uid) from event_log);")
    EventLogEntity getLastRecord();

    @Query("Delete from event_log where uid<:currentTime")
    void deleteOldRecord(long currentTime);

    @Query("select * from event_log order by uid desc limit 10;")
    List<EventLogEntity> getLatestTenLog();

    //@Query("SELECT time_stamp, count(*) FROM event_log group by time_stamp")
    //List<String> selectDistinctRecord();
}