package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.zebrapoc.service.EventTrackingService;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, "Booting", Toast.LENGTH_SHORT).show();

        /*AlertDialog.Builder  builder = new AlertDialog.Builder(context);
        builder.setMessage("Booting");
        builder.show();*/

        Log.d(TAG, "onReceive: ");
        Intent serviceIntent = new Intent(context, EventTrackingService.class);
        serviceIntent.putExtra("inputExtra", "arnab");
        ContextCompat.startForegroundService(context, serviceIntent);
    }
}