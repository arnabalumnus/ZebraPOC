package com.example.zebrapoc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String getTimeStamp(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
    }

    public static String getTimeStampFileName(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy HH_mm_ss");
        return format.format(date);
    }
}
