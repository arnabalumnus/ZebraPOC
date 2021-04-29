package com.alumnus.zebra;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

import com.alumnus.zebra.broadcastReceiver.BootReceiver;
import com.alumnus.zebra.broadcastReceiver.PowerConnectionReceiver;

public class ZebraApp extends Application {

    public static final String CHANNEL_ID = "EventTrackingServiceChannel";
    @Override
    public void onCreate() {
        super.onCreate();
        /*PowerConnectionReceiver receiver = new PowerConnectionReceiver();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        iFilter.addAction(Intent.ACTION_BATTERY_LOW);
        iFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        registerReceiver(receiver, iFilter);*/

        BootReceiver bootReceiver = new BootReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction(Intent.ACTION_REBOOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intentFilter.addAction(Intent.ACTION_LOCKED_BOOT_COMPLETED);
        }
        registerReceiver(bootReceiver, intentFilter);

        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
