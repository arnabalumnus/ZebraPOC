package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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

        Intent serviceIntent = new Intent(context, LifeTimeService.class);
        serviceIntent.putExtra("frequency", 5);
        ContextCompat.startForegroundService(context, serviceIntent);

    }
}