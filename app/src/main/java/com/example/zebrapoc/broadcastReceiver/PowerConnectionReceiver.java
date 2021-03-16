package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.zebrapoc.service.EventTrackingService;
import com.example.zebrapoc.utils.ExportFile;

public class PowerConnectionReceiver extends BroadcastReceiver {

    public PowerConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show();
            //Intent serviceIntent = new Intent(context, EventTrackingService.class);
            //context.stopService(serviceIntent);

            ExportFile.exportDataIntoCSVFile(context, "onPowerConnected");
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "POWER DISCONNECTED", Toast.LENGTH_SHORT).show();
            //Intent serviceIntent = new Intent(context, EventTrackingService.class);
            //serviceIntent.putExtra("inputExtra", "Accelerometer running");
            //ContextCompat.startForegroundService(context, serviceIntent);
        }
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "BATTERY LOW", Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
            Toast.makeText(context, "BATTERY OKAY", Toast.LENGTH_LONG).show();
        }

        //Calling repeatedly
        /* if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            Toast.makeText(context, "BATTERY CHANGED", Toast.LENGTH_LONG).show();
        }*/
    }
}