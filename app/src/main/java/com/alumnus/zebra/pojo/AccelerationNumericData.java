package com.alumnus.zebra.pojo;


/**
 * Data class for Acceleration
 * Contains acceleration Numeric values
 * @author Arnab Kundu
 */
public class AccelerationNumericData {

    public long ts;
    public float x;
    public float y;
    public float z;

    public AccelerationNumericData(long ts, float x, float y, float z) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}