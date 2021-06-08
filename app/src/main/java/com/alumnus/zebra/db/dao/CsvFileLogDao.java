package com.alumnus.zebra.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alumnus.zebra.db.entity.CsvFileLogEntity;

import java.util.List;

@Dao
public interface CsvFileLogDao {

    @Query("SELECT * FROM csv_file_log")
    List<CsvFileLogEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CsvFileLogEntity... csvFileLogs);

    @Query("DELETE FROM csv_file_log WHERE file_name=:file_name")
    void deleteCSVRecord(String file_name);

    @Query("SELECT file_name FROM csv_file_log ORDER BY id ASC LIMIT 1")
    String getOldestCSVFile();

    @Query("SELECT SUM(count) as total_records FROM csv_file_log")
    long getTotalRecordOfAllCSVFile();

    @Query("SELECT count(*) FROM csv_file_log")
    int getCSVFileCount();
}