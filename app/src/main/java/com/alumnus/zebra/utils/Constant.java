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

    /**
     * @DATA_CHUNK_SIZE of 130000 approximately makes
     * 5MB size of .csv file
     * &
     * 43 mints of data @50Hz(50 records/sec)
     */
    public static final int DATA_CHUNK_SIZE = 20000;
    public static final int RETAIN_NUMBER_OF_CSV_FILE = 100;
}
