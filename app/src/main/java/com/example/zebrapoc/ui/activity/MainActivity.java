package com.example.zebrapoc.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.EventLogEntity;
import com.example.zebrapoc.service.EventTrackingService;
import com.example.zebrapoc.utils.DateFormatter;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToAccelerometer(View view) {
        startActivity(new Intent(this, AccelerometerActivity.class));
    }


    public void startService(View v) {
        //String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, EventTrackingService.class);
        serviceIntent.putExtra("inputExtra", "arnab");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, EventTrackingService.class);
        stopService(serviceIntent);
    }

    public void navigateToDatabase(View view) {
        startActivity(new Intent(this, DatabaseActivity.class));
    }

    //region Export Data and save into SD card or Phone Storage
    public void exportDataButton(View view) {
        if (isStoragePermissionGranted()) {
            exportDataIntoCSVFile();
        }
    }

    public void exportDataIntoCSVFile() {
        Runnable runnable = () -> {
            File exportDir;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                exportDir = new File(getExternalFilesDir("Accelerometer"), "ZebraApp"); // Working in API 30 i.e. Android 11 and higher
            else
                exportDir = new File(Environment.getExternalStorageDirectory(), "ZebraApp"); // Working in API 29 i.e. Android 10 and lower
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "Zebra_" + DateFormatter.getTimeStampFileName(System.currentTimeMillis()) + ".csv");
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                List<EventLogEntity> eventLogEntityList = db.eventLogDao().getAll();
                csvWrite.writeNext(new String[]{"id", "time_stamp", "event", "x", "y", "z"});
                for (EventLogEntity eventLogEntity : eventLogEntityList) {
                    //Which column you want to export
                    String arrStr[] = {String.valueOf(eventLogEntity.uid), eventLogEntity.time_stamp, eventLogEntity.event, String.valueOf(eventLogEntity.x), String.valueOf(eventLogEntity.y), String.valueOf(eventLogEntity.z)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                Log.e("csv", "exportData: Data Exported");
            } catch (Exception sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            exportDataIntoCSVFile();
        }
    }
    //endregion
}