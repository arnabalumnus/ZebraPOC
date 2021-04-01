package com.alumnus.zebra.pojo;

public class AccelerationData {

    public long ts;
    public float x;
    public float y;
    public float z;

    public AccelerationData(long ts, float x, float y, float z) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}