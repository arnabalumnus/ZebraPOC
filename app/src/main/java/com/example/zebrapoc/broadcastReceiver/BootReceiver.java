package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.zebrapoc.service.LifeTimeService;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, " onReceive()");
        Toast.makeText(context, "Booting", Toast.LENGTH_LONG).show();
        SharedPreferences sp = context.getSharedPreferences("Zebra", Context.MODE_PRIVATE);
        int frequency = sp.getInt("frequency", 5);
        Intent serviceIntent = new Intent(context, LifeTimeService.class);
        serviceIntent.putExtra("frequency", frequency);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            ContextCompat.startForegroundService(context, serviceIntent);
        else
            context.startService(intent);


    }
}