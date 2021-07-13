package com.alumnus.zebra.pojo;


/**
 * Data class for Acceleration
 * Contains acceleration String values
 * @author Arnab Kundu
 */
public class AccelerationStringData {

    public String ts;
    public String x;
    public String y;
    public String z;

    public AccelerationStringData(String ts, String x, String y, String z) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}