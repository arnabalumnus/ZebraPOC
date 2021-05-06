package com.alumnus.zebra.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.alumnus.zebra.db.entity.AccLogEntity;

import java.util.List;

@Dao
public interface AccLogDao {

    @Query("SELECT * FROM accelerometer_log")
    List<AccLogEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AccLogEntity... accelerometer_log);

    @Delete
    void delete(AccLogEntity accelerometer_log);

    @Query("Select count(*) from accelerometer_log;")
    long getCount();

    @Query("Delete from accelerometer_log where ts <:time_stamp")
    void deleteAll(long time_stamp);

    @Query("Select MAX(TS), MIN(TS) from accelerometer_log;")
    long[] getLastRecordTime();


    //region Get data and clear the same from DB

    /**
     * processDataChunk() takes size as params and return List of AccLogEntity
     *
     * @param data_chunk_size Size of List<AccLogEntity> data
     * @return List<AccLogEntity>
     * @usage How to call this
     * AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
     * List<AccLogEntity> accLogEntities = db.accLogDao().processDataChunk(10);
     */
    @Transaction()
    default List<AccLogEntity> processDataChunk(int data_chunk_size) {
        List<AccLogEntity> data = getDataChunk(data_chunk_size);
        deleteChunk(data_chunk_size);
        return data;
    }

    @Query("SELECT * FROM accelerometer_log ORDER BY TS ASC limit :chunk_size")
    List<AccLogEntity> getDataChunk(int chunk_size);

    @Query("DELETE FROM accelerometer_log WHERE id <=(SELECT id FROM accelerometer_log LIMIT 1 OFFSET :chunk_size-1)")
    void deleteChunk(int chunk_size);
    //endregion

    //@Query("SELECT DISTINCT TS, count(*) FROM accelerometer_log")
    //List<String> selectDistinctRecord();

    //@Query("SELECT TS, count(*) FROM accelerometer_log group by TS")
    //List<String> selectDistinctRecord();

    @Query("SELECT TS FROM accelerometer_log limit 1")
    long getStartingTimeStamp();
}