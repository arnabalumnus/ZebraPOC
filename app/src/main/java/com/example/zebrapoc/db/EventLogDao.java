package com.example.zebrapoc.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

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

    //@Query("Select time_stamp, count(*) from event_log group by time_stamp;")
    //Object getGroupOfRecord();

    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);*/
}