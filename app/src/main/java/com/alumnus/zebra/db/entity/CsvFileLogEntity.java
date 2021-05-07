package com.alumnus.zebra.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "csv_file_log", indices = {@Index(value = {"file_name"}, unique = true)})
public class CsvFileLogEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "file_name")
    public String file_name;

    @ColumnInfo(name = "count")
    public long count;

    public CsvFileLogEntity(String file_name, long count) {
        this.file_name = file_name;
        this.count = count;
    }
}