package com.alumnus.zebra.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.AsyncTask
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.alumnus.zebra.R
import com.alumnus.zebra.db.AppDatabase
import com.alumnus.zebra.db.entity.AccLogEntity

class LifeTimeService : Service(), SensorEventListener {
    //private final String TAG = this.getClass().getSimpleName();
    private var db: AppDatabase? = null
    private var accLogEntity: AccLogEntity? = null
    private var G_towards_X = 0f
    private var G_towards_Y = 0f
    private var G_towards_Z = 0f
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val frequency = intent.extras!!.getInt("frequency", 5)
        //Log.d(TAG, "onStartCommand: frequency=" + frequency);
        val mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (frequency == 50) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW) //Value=1 , Frequency: ~50/sec
        else if (frequency == 15) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM) //Value=2 , Frequency: ~15/sec
        else if (frequency == 5) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH) //Value=3 , Frequency: ~5/sec
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        accLogEntity = AccLogEntity()
        runAsForeground()
        return START_NOT_STICKY
    }

    private fun runAsForeground() {
        val notificationIntent = Intent(this, this.javaClass)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
        val mNotificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel("secondary", " secondary Channel", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "secondary Channel"
            channel.setShowBadge(true)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, "secondary")
                .setNumber(0)
                .setOngoing(true)
                .setColorized(true)
                .setColor(Color.parseColor("#085A0B"))
                .setSmallIcon(R.drawable.ic_notification_small)
                .setSubText("Accelerometer is running...")
                .setContentTitle("Zebra")
                .setContentText("Accelerometer is running...")
                .setSilent(true)
                .setContentIntent(pendingIntent)
        startForeground(12345, notification.build())
    }

    override fun onSensorChanged(event: SensorEvent) {
        try {
            G_towards_X = event.values[0]
            G_towards_Y = event.values[1]
            G_towards_Z = event.values[2]
            //Log.i(TAG, "onSensorChanged: X:" + G_towards_X + " Y:" + G_towards_Y + " Z:" + G_towards_Z);
            accLogEntity!!.ts = System.currentTimeMillis()
            accLogEntity!!.x = G_towards_X
            accLogEntity!!.y = G_towards_Y
            accLogEntity!!.z = G_towards_Z

            //eventLogEntity.setUid(System.currentTimeMillis());
            //eventLogEntity.setTime_stamp(getTimeStamp(System.currentTimeMillis()));
            //eventLogEntity.setEvent("No event");
            //eventLogEntity.setX(G_towards_X);
            //eventLogEntity.setY(G_towards_Y);
            //eventLogEntity.setZ(G_towards_Z);
            //TODO
            if (db != null) {
                object : AsyncTask<Void, Void, Unit>() {
                    override fun doInBackground(vararg voids: Void) {
                        db!!.accLogDao().insert(accLogEntity!!)
                    }
                }.execute()
            }
        } catch (e: Exception) {
            //Log.e(TAG, e.getMessage());
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}