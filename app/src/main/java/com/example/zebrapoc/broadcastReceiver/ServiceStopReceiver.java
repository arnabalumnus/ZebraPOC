package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.zebrapoc.service.EventTrackingService;

public class ServiceStopReceiver extends BroadcastReceiver {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, " onReceive()");

        String method = intent.getExtras().getString("method");
        Toast.makeText(context, "Service Stopped " + method, Toast.LENGTH_LONG).show();

        /**
         Intent serviceIntent = new Intent(context, EventTrackingService.class);
         serviceIntent.putExtra("inputExtra", "arnab");
         ContextCompat.startForegroundService(context, serviceIntent);
         */
    }
}