package com.example.zebrapoc.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.AccLogEntity;
import com.example.zebrapoc.db.entity.EventLogEntity;
import com.example.zebrapoc.ui.activity.MainActivity;

import static com.example.zebrapoc.utils.DateFormatter.getTimeStamp;

public class LifeTimeService extends Service implements SensorEventListener {

    private final String TAG = this.getClass().getSimpleName();
    private AppDatabase db;
    //private int mStartId;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);    //Value=1 , Rate=48~50/sec XT 50~51/sec
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM); //Value=2 , Rate=~15/sec XT 15/sec
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);   //Value=3 , Rate=~15/sec , XT 5/sec
        if (db == null)
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

        runAsForeground();
        return super.onStartCommand(intent, flags, startId);
    }

    private void runAsForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("secondary", " secondary Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("secondary Channel");
            channel.setShowBadge(true);
            mNotificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, "secondary")
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                .setNumber(0)
                .setOngoing(true)
                .setColor(Color.parseColor("#00FF00"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setNotificationSilent()
                .setContentTitle("Zebra")
                .setContentText("Accelerometer is running...")
                .setContentIntent(pendingIntent).build();

        startForeground(12345, notification);
        //ShortcutBadger.removeCount(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float G_towards_X = event.values[0];
        float G_towards_Y = event.values[1];
        float G_towards_Z = event.values[2];
        Log.w(TAG, "onSensorChanged: X:" + G_towards_X + " Y:" + G_towards_Y + " Z:" + G_towards_Z);

        if (db != null) {
            Runnable runnable = () -> {
                db.eventLogDao().insert(new EventLogEntity(System.currentTimeMillis(), getTimeStamp(System.currentTimeMillis()), "No event", G_towards_X, G_towards_Y, G_towards_Z));
                db.accLogDao().insert(new AccLogEntity(System.currentTimeMillis(), G_towards_X, G_towards_Y, G_towards_Z));
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
