package com.alumnus.zebra.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import static android.content.Context.WIFI_SERVICE;

public class IpAddress {

    public static String getIPAddress(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }
}
