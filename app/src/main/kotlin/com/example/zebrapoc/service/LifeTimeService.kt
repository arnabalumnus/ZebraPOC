package com.example.zebrapoc.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.example.zebrapoc.R
import com.example.zebrapoc.db.AppDatabase
import com.example.zebrapoc.db.entity.AccLogEntity
import com.example.zebrapoc.ui.activity.MainActivity
import com.example.zebrapoc.utils.Coroutines

class LifeTimeService : Service(), SensorEventListener {
    //private val TAG = this.javaClass.simpleName
    private lateinit var db: AppDatabase
    private lateinit var accLogEntity: AccLogEntity
    private var G_towards_X = 0f
    private var G_towards_Y = 0f
    private var G_towards_Z = 0f

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val frequency = intent.extras!!.getInt("frequency", 5)
        //Log.d(TAG, "onStartCommand: frequency=$frequency")
        val mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (frequency == 50) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW) //Value=1 , Rate=48~50/sec XT 50~51/sec
        else if (frequency == 15) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM) //Value=2 , Rate=~15/sec XT 15/sec
        else if (frequency == 5) mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH) //Value=3 , Rate=~15/sec , XT 5/sec
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        /* object : AsyncTask<Void?, Void?, Void?>() {
             protected override fun doInBackground(vararg voids: Void): Void? {
                 db!!.logDao().insert(LogEntity("onStartCommand", DateFormatter.getTimeStampFileName(System.currentTimeMillis())))
                 return null
             }
         }.execute()*/
        accLogEntity = AccLogEntity()
        runAsForeground()
        //return START_NOT_STICKY;
        //return START_STICKY;
        return START_REDELIVER_INTENT
    }

    private fun runAsForeground() {
        val notificationIntent = Intent(this, MainActivity::class.java)
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
                .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.notification_small))
                .setNumber(0)
                .setOngoing(true)
                .setColorized(true)
                .setColor(Color.parseColor("#085A0B"))
                .setSmallIcon(R.drawable.ic_notification_small)
                .setSubText("Accelerometer is running...")
                .setContentTitle("Zebra")
                .setContentText("Accelerometer is running...")
                .setSilent(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
        startForeground(12345, notification.build())
        //ShortcutBadger.removeCount(this);
    }

    override fun onSensorChanged(event: SensorEvent) {
        G_towards_X = event.values[0]
        G_towards_Y = event.values[1]
        G_towards_Z = event.values[2]
        accLogEntity.setTs(System.currentTimeMillis())
        accLogEntity.setX(G_towards_X)
        accLogEntity.setY(G_towards_Y)
        accLogEntity.setZ(G_towards_Z)
        Coroutines.IO {
            db.accLogDao().insert(accLogEntity)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    /*override fun onLowMemory() {
        try {
            object : AsyncTask<Void?, Void?, Void?>() {
                protected override fun doInBackground(vararg voids: Void): Void? {
                    db!!.logDao().insert(LogEntity("onLowMemory", DateFormatter.getTimeStampFileName(System.currentTimeMillis())))
                    return null
                }
            }.execute()
        } catch (e: Exception) {
            Log.e(TAG, "onTrimMemory: $e")
        }
        val intent = Intent(applicationContext, ServiceStopReceiver::class.java)
        intent.action = "com.example.LifeTimeService.stopped"
        intent.putExtra("method", "onLowMemory()")
        sendBroadcast(intent)
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        val memory_info: String
        memory_info = when (level) {
            TRIM_MEMORY_RUNNING_CRITICAL -> "TRIM_MEMORY_RUNNING_CRITICAL"
            TRIM_MEMORY_RUNNING_LOW -> "TRIM_MEMORY_RUNNING_LOW"
            TRIM_MEMORY_RUNNING_MODERATE -> "TRIM_MEMORY_RUNNING_MODERATE"
            else -> "" + level
        }
        try {
            object : AsyncTask<Void?, Void?, Void?>() {
                protected override fun doInBackground(vararg voids: Void): Void? {
                    db!!.logDao().insert(LogEntity("onTrimMemory level:$memory_info", DateFormatter.getTimeStampFileName(System.currentTimeMillis())))
                    return null
                }
            }.execute()
        } catch (e: Exception) {
            Log.e(TAG, "onTrimMemory: $e")
        }
        val intent = Intent(applicationContext, ServiceStopReceiver::class.java)
        intent.action = "com.example.LifeTimeService.stopped"
        intent.putExtra("method", "onTrimMemory() Level: $memory_info")
        sendBroadcast(intent)
        super.onTrimMemory(level)
    }*/
}