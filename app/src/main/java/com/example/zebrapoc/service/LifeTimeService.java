package com.example.zebrapoc.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.zebrapoc.R;
import com.example.zebrapoc.broadcastReceiver.ServiceStopReceiver;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.AccLogEntity;
import com.example.zebrapoc.db.entity.LogEntity;
import com.example.zebrapoc.ui.activity.MainActivity;
import com.example.zebrapoc.utils.DateFormatter;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LifeTimeService extends Service implements SensorEventListener {

    private final String TAG = this.getClass().getSimpleName();
    private AppDatabase db;
    //private EventLogEntity eventLogEntity;
    private AccLogEntity accLogEntity;
    //private int mStartId;
    private float G_towards_X = 0;
    private float G_towards_Y = 0;
    private float G_towards_Z = 0;


    public LifeTimeService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int frequency = intent.getExtras().getInt("frequency", 5);
        Log.d(TAG, "onStartCommand: frequency=" + frequency);
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (frequency == 50)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);    //Value=1 , Rate=48~50/sec XT 50~51/sec
        else if (frequency == 15)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM); //Value=2 , Rate=~15/sec XT 15/sec
        else if (frequency == 5)
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);   //Value=3 , Rate=~15/sec , XT 5/sec
        if (db == null)
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.logDao().insert(new LogEntity("onStartCommand", DateFormatter.getTimeStampFileName(System.currentTimeMillis())));
                return null;
            }
        }.execute();

        //eventLogEntity = new EventLogEntity();
        accLogEntity = new AccLogEntity();
        runAsForeground();
        //return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
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

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "secondary")
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                .setNumber(0)
                .setOngoing(true)
                .setColorized(true)
                .setColor(Color.parseColor("#085A0B"))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setSubText("Accelerometer is running...")
                .setContentTitle("Zebra")
                .setContentText("Accelerometer is running...")
                .setNotificationSilent()
                .setContentIntent(pendingIntent);

        startForeground(12345, notification.build());
        //ShortcutBadger.removeCount(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            G_towards_X = event.values[0];
            G_towards_Y = event.values[1];
            G_towards_Z = event.values[2];
            //Log.w(TAG, "onSensorChanged: X:" + G_towards_X + " Y:" + G_towards_Y + " Z:" + G_towards_Z);

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
                        //db.eventLogDao().insert(eventLogEntity);
                        db.accLogDao().insert(accLogEntity);
                        return null;
                    }
                }.execute();
            }
        } catch (Exception e) {
            Log.e(TAG, "onSensorChanged: " + e);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLowMemory() {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db.logDao().insert(new LogEntity("onLowMemory", DateFormatter.getTimeStampFileName(System.currentTimeMillis())));
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            Log.e(TAG, "onTrimMemory: " + e);
        }
        Intent intent = new Intent(getApplicationContext(), ServiceStopReceiver.class);
        intent.setAction("com.example.LifeTimeService.stopped");
        intent.putExtra("method", "onLowMemory()");
        sendBroadcast(intent);
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db.logDao().insert(new LogEntity("onTrimMemory level:" + level, DateFormatter.getTimeStampFileName(System.currentTimeMillis())));
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            Log.e(TAG, "onTrimMemory: " + e);
        }
        Intent intent = new Intent(getApplicationContext(), ServiceStopReceiver.class);
        intent.setAction("com.example.LifeTimeService.stopped");
        intent.putExtra("method", "onTrimMemory() Level: " + level);
        sendBroadcast(intent);
        super.onTrimMemory(level);
    }

    @Override
    public void onDestroy() {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db.logDao().insert(new LogEntity("onDestroy", DateFormatter.getTimeStampFileName(System.currentTimeMillis())));
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            Log.e(TAG, "onDestroy: " + e);
        }
        //db.close();
        Intent intent = new Intent(getApplicationContext(), ServiceStopReceiver.class);
        intent.setAction("com.example.LifeTimeService.stopped");
        intent.putExtra("method", "onDestroy()");
        sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
