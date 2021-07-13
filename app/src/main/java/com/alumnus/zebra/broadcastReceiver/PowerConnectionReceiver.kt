package com.alumnus.zebra.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.alumnus.zebra.machineLearning.utils.ExportFiles;
import com.alumnus.zebra.service.LifeTimeService;

public class PowerConnectionReceiver extends BroadcastReceiver {

    public PowerConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show();

            //region Stop service on power connect
            /*Intent serviceIntent = new Intent(context, LifeTimeService.class);
            context.stopService(serviceIntent);*/
            //endregion

            ExportFiles.INSTANCE.prepareDataChunk(context,false);
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "POWER DISCONNECTED", Toast.LENGTH_SHORT).show();

            //region Start service on power disconnect
            SharedPreferences sp = context.getSharedPreferences("Zebra", Context.MODE_PRIVATE);
            int frequency = sp.getInt("frequency", 5);
            Intent serviceIntent = new Intent(context, LifeTimeService.class);
            serviceIntent.putExtra("frequency", frequency);
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                ContextCompat.startForegroundService(context, serviceIntent);
            else
                context.startService(intent);*/

            //endregion
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