package com.alumnus.zebra.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.alumnus.zebra.R;
import com.alumnus.zebra.db.AppDatabase;
import com.alumnus.zebra.db.entity.AccLogEntity;

public class LifeTimeService extends Service implements SensorEventListener {

    //private final String TAG = this.getClass().getSimpleName();
    private AppDatabase db;
    private AccLogEntity accLogEntity;
    private float G_towards_X = 0;
    private float G_towards_Y = 0;
    private float G_towards_Z = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int frequency = intent.getExtras().getInt("frequency", 5);
        //Log.d(TAG, "onStartCommand: frequency=" + frequency);
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (frequency == 50)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);    //Value=1 , Frequency: ~50/sec
        else if (frequency == 15)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM); //Value=2 , Frequency: ~15/sec
        else if (frequency == 5)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);   //Value=3 , Frequency: ~5/sec
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        accLogEntity = new AccLogEntity();
        runAsForeground();
        return START_NOT_STICKY;
    }

    private void runAsForeground() {
        Intent notificationIntent = new Intent(this, this.getClass());
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

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "secondary")
                .setNumber(0)
                .setOngoing(true)
                .setColorized(true)
                .setColor(Color.parseColor("#085A0B"))
                .setSmallIcon(R.drawable.ic_notification_small3)
                .setSubText("Accelerometer is running...")
                .setContentTitle("Zebra")
                .setContentText("Accelerometer is running...")
                .setSilent(true)
                .setContentIntent(pendingIntent);

        startForeground(12345, notification.build());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            G_towards_X = event.values[0];
            G_towards_Y = event.values[1];
            G_towards_Z = event.values[2];
            //Log.i(TAG, "onSensorChanged: X:" + G_towards_X + " Y:" + G_towards_Y + " Z:" + G_towards_Z);

            accLogEntity.setTs(System.currentTimeMillis());
            accLogEntity.setX(G_towards_X);
            accLogEntity.setY(G_towards_Y);
            accLogEntity.setZ(G_towards_Z);

            //eventLogEntity.setUid(System.currentTimeMillis());
            //eventLogEntity.setTime_stamp(getTimeStamp(System.currentTimeMillis()));
            //eventLogEntity.setEvent("No event");
            //eventLogEntity.setX(G_towards_X);
            //eventLogEntity.setY(G_towards_Y);
            //eventLogEntity.setZ(G_towards_Z);

            if (db != null) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        db.accLogDao().insert(accLogEntity);
                        return null;
                    }
                }.execute();
            }
        } catch (Exception e) {
            //Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
