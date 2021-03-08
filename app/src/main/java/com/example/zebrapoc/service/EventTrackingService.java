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
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.EventLogEntity;
import com.example.zebrapoc.ui.activity.MainActivity;
import com.example.zebrapoc.utils.DateFormatter;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long futureTime = System.currentTimeMillis() + 1000 * 60 * 15;
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
        t.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        Log.e(TAG, "startId: " + startId);
        this.mStartId = startId;
        //String input = intent.getStringExtra("inputExtra");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);    //Value=1 , Rate=48~50/sec
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM); //Value=2 , Rate=~15/sec
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);   //Value=3 , Rate=~15/sec
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText("Arnab")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.FLAG_FOREGROUND_SERVICE)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread

        //stopSelf();

        return START_STICKY;
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
        //stopSelf(mStartId);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "onTrimMemory: ");
        //stopSelf(mStartId);
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
        Log.e(TAG, " :Start the NEVER ENDING task: ");
        //stopSelf(mStartId);
        exportDataIntoCSVFile();

        if (mSensorManager == null) {
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //mSensorManager.unregisterListener(this);

            //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);    //Value=1 , Rate=48~50/sec
            //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM); //Value=2 , Rate=~15/sec
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);   //Value=3 , Rate=~15/sec
        }
        if (db == null)
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
    }

    private void deleteRecordsUpTo() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        db.eventLogDao().deleteOldRecord(System.currentTimeMillis());
    }

    public void exportDataIntoCSVFile() {
        Runnable runnable = () -> {
            File exportDir;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                exportDir = new File(getExternalFilesDir("Accelerometer"), "ZebraApp"); // Working in API 30 i.e. Android 11 and higher
            else
                exportDir = new File(Environment.getExternalStorageDirectory(), "ZebraApp"); // Working in API 29 i.e. Android 10 and lower
            if (!exportDir.exists()) {
                if (!exportDir.mkdirs()) {
                    Log.e(TAG, "Error in mkdirs");
                    return;
                }
            }

            File file = new File(exportDir, DateFormatter.getTimeStampFileName(System.currentTimeMillis()) + ".csv");
            try {
                if (!file.createNewFile()) {
                    Log.e(TAG, "Error in createNewFile");
                    return;
                }
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                List<EventLogEntity> eventLogEntityList = db.eventLogDao().getAll();
                csvWrite.writeNext(new String[]{"id", "time_stamp", "event", "x", "y", "z"});
                for (EventLogEntity eventLogEntity : eventLogEntityList) {
                    //Which column you want to export
                    String[] arrStr = {String.valueOf(eventLogEntity.uid), eventLogEntity.time_stamp, eventLogEntity.event, String.valueOf(eventLogEntity.x), String.valueOf(eventLogEntity.y), String.valueOf(eventLogEntity.z)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                Log.e("csv", "exportData: Data Exported");
                deleteRecordsUpTo();
            } catch (Exception sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
