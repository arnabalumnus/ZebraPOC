package com.alumnus.zebra.utils;

public class Constant {

    /**
     * G_calibration_factor value need to decrease gradually to be more accurate about a free fall
     */
    public static final float G_CALIBRATION_FACTOR = 5.89F; //value need to decrease gradually to be more accurate about a free fall

    /**
     * throw_calibration_factor value need to increase gradually to be more accurate about a throw
     */
    public static final float THROW_CALIBRATION_FACTOR = 18.81F; //value need to decrease gradually to be more accurate about a free fall

    public static final String SP = "ZebraSp";
    public static final String isAutoStartPermissionGranted = "isAutoStartPermissionGranted";

    public static final int DATA_CHUNK_SIZE = 5 * 60;
}
