package com.example.zebrapoc.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_log")
public class EventLogEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "uid")
    public long uid;

    @ColumnInfo(name = "time_stamp")
    public String time_stamp;

    @ColumnInfo(name = "event")
    public String event;

    @ColumnInfo(name = "x")
    public float x;

    @ColumnInfo(name = "y")
    public float y;

    @ColumnInfo(name = "z")
    public float z;

    public EventLogEntity(long uid, String time_stamp, String event, float x, float y, float z) {
        this.uid = uid;
        this.time_stamp = time_stamp;
        this.event = event;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}