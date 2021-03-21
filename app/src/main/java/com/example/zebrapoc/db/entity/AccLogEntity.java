package com.example.zebrapoc.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accelerometer_log")
public class AccLogEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "TS")
    public long ts;

    @ColumnInfo(name = "X")
    public float x;

    @ColumnInfo(name = "Y")
    public float y;

    @ColumnInfo(name = "Z")
    public float z;

    public AccLogEntity() {
    }

    public AccLogEntity(long ts, float x, float y, float z) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTs(long ts) {
        this.ts = ts;
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