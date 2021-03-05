package com.example.zebrapoc.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.zebrapoc.ui.activity.MainActivity;
import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.EventLogEntity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.zebrapoc.ZebraApp.CHANNEL_ID;


public class EventTrackingService extends Service implements SensorEventListener {

    private final String TAG = this.getClass().getSimpleName();
    private AppDatabase db;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int mStartId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.e(TAG, "attachBaseContext: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        Log.e(TAG, "startId: " + startId);
        this.mStartId = startId;
        String input = intent.getStringExtra("inputExtra");
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.FLAG_FOREGROUND_SERVICE)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
        /*Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long futureTime = System.currentTimeMillis() + 5000;
                    while (System.currentTimeMillis() < futureTime) {
                        synchronized (this) {
                            //Log.i(TAG, "Service is running");
                            try {
                                wait(futureTime - System.currentTimeMillis());
                                // TODO implement the never ending job in service
                                startTheNeverEndingTask();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();*/
        //stopSelf();

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "onLowMemory: ");
        stopSelf(mStartId);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "onTrimMemory: ");
        stopSelf(mStartId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind: ");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG, "onTaskRemoved: ");
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
        Log.e(TAG, "dump: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG, "onAccuracyChanged: ");
    }

    public void onSensorChanged(SensorEvent event) {
        Log.e("msg", "onSensorChanged: ");
        float G_towards_X = event.values[0];
        float G_towards_Y = event.values[1];
        float G_towards_Z = event.values[2];

       /* if (Math.abs(G_towards_X) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Y) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Z) < G_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a free fall");
            if (db != null) {
                Runnable runnable = () -> db.eventLogDao().insertAll(new EventLogEntity(System.currentTimeMillis(), getTimeStamp(System.currentTimeMillis()), "Free fall", G_towards_X, G_towards_Y, G_towards_Z));
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }

        if (Math.abs(G_towards_X) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Y) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Z) > THROW_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a throw");
            if (db != null) {
                Runnable runnable = () -> db.eventLogDao().insertAll(new EventLogEntity(System.currentTimeMillis(), getTimeStamp(System.currentTimeMillis()), "Throw", G_towards_X, G_towards_Y, G_towards_Z));
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }*/

        if (db != null) {
            Runnable runnable = () -> db.eventLogDao().insert(new EventLogEntity(System.currentTimeMillis(), getTimeStamp(System.currentTimeMillis()), "No event", G_towards_X, G_towards_Y, G_towards_Z));
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    private String getTimeStamp(long currentTimeInMilliSec) {
        Date date = new Date(currentTimeInMilliSec);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
    }

    private void startTheNeverEndingTask() {
        Log.d(TAG, " :Start the NEVER ENDING task: ");
    }
}
