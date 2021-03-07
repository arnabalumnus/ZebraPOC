package com.example.zebrapoc.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {


    private final String TAG = this.getClass().getSimpleName();

    public PowerConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        //intent.getExtras().get("android.intent.action.ACTION_POWER_CONNECTED");
        Toast.makeText(context, "Charging/Discharging", Toast.LENGTH_LONG).show();
    }
}