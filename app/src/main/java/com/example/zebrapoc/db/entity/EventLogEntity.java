package com.example.zebrapoc.db.entity;

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

    public EventLogEntity() {
    }

    public EventLogEntity(long uid, String time_stamp, String event, float x, float y, float z) {
        this.uid = uid;
        this.time_stamp = time_stamp;
        this.event = event;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}