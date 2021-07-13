package com.alumnus.zebra.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alumnus.zebra.service.LifeTimeService

class BootReceiver : BroadcastReceiver() {

    //private val TAG = "BootReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        //Log.d(TAG, " onReceive()")
        Toast.makeText(context, "Booting", Toast.LENGTH_LONG).show()
        val sp = context.getSharedPreferences("Zebra", Context.MODE_PRIVATE)
        val frequency = sp.getInt("frequency", 5)
        val serviceIntent = Intent(context, LifeTimeService::class.java)
        serviceIntent.putExtra("frequency", frequency)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ContextCompat.startForegroundService(context, serviceIntent) else context.startService(intent)
    }

}