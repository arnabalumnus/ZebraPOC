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

    public AccLogEntity(long ts, float x, float y, float z) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}