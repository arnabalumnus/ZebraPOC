package com.alumnus.zebra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Formats date and time from millisecond
 */
public class DateFormatter {

    /**
     * Note: Contains spacial character. Don't use for filename.
     *
     * @param currentTimeInMilliSec     Current System time in millisecond.
     * @return                          A string that contains date and time with spacial character.
     */
    public static String getTimeStamp(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    /**
     * Supports for filename. Doesn't contains any spacial character that doesn't support in filename
     *
     * @param currentTimeInMilliSec     Current System time in millisecond.
     * @return                          A string that contains date and time for filename.
     */
    public static String getTimeStampFileName(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("yyyy, MMM-dd 'Time-'HH mm ss", Locale.getDefault());
        return format.format(date);
    }

    /**
     * Supports for filename. Doesn't contains any spacial character that doesn't support in filename
     *
     * @param currentTimeInMilliSec     Current System time in millisecond.
     * @return                          A string that contains only date for filename.
     */
    public static String getDateStampForFileName(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        return format.format(date);
    }
}
