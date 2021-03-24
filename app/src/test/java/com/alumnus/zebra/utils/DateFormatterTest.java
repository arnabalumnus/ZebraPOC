package com.alumnus.zebra.utils;

import org.junit.Test;

public class DateFormatterTest {

    @Test
    public void getTimeStamp() {
        long timeInMillisecond = 1615964706453L;
        String time_stamp = DateFormatter.getTimeStamp(timeInMillisecond);
        System.out.println(time_stamp);
    }

    @Test
    public void getTimeStampFileName() {
        long timeInMillisecond = 1615964706453L;
        String time_stamp = DateFormatter.getTimeStampFileName(timeInMillisecond);
        System.out.println(time_stamp);
    }
}