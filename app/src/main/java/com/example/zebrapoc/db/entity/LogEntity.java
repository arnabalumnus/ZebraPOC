package com.example.zebrapoc.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log")
public class LogEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "msg")
    public String msg;

    @ColumnInfo(name = "time_stamp")
    public String time_stamp;

    public LogEntity(String msg, String time_stamp) {
        this.msg = msg;
        this.time_stamp = time_stamp;
    }
}