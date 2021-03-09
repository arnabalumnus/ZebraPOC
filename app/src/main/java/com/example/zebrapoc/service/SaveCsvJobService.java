package com.example.zebrapoc.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.room.Room;

import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.EventLogEntity;
import com.example.zebrapoc.utils.DateFormatter;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * https://codinginflow.com/tutorials/android/jobscheduler
 */

public class SaveCsvJobService extends JobService {
    private static final String TAG = "SaveCsvJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
        return true;
    }

    /**
     * Export records into a .csv file
     * Delete old records that already exported into csv file
     * @param params
     */
    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File exportDir;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                    exportDir = new File(getExternalFilesDir("Accelerometer"), "ZebraApp"); // Working in API 30 i.e. Android 11 and higher
                else
                    exportDir = new File(Environment.getExternalStorageDirectory(), "ZebraApp/JobService"); // Working in API 29 i.e. Android 10 and lower
                if (!exportDir.exists()) {
                    if(!exportDir.mkdirs()){
                        Log.e(TAG, "Error in mkdirs" );
                        return;
                    }
                }

                File file = new File(exportDir, DateFormatter.getTimeStampFileName(System.currentTimeMillis()) + ".csv");
                try {
                    if(!file.createNewFile()){
                        Log.e(TAG, "Error in createNewFile" );
                        return;
                    }
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

                    List<EventLogEntity> eventLogEntityList = db.eventLogDao().getAll();
                    csvWrite.writeNext(new String[]{"id", "time_stamp", "event", "x", "y", "z"});
                    for (EventLogEntity eventLogEntity : eventLogEntityList) {
                        //Which column you want to export
                        String[] arrStr = {String.valueOf(eventLogEntity.uid), eventLogEntity.time_stamp, eventLogEntity.event, String.valueOf(eventLogEntity.x), String.valueOf(eventLogEntity.y), String.valueOf(eventLogEntity.z)};
                        csvWrite.writeNext(arrStr);
                    }
                    csvWrite.close();
                    Log.e(TAG, "exportData: Data Exported");
                    /**
                     * DELETE OLD RECORDS
                     */
                    db.eventLogDao().deleteOldRecord(System.currentTimeMillis());
                    Log.e(TAG, "Old records DELETED");
                } catch (Exception sqlEx) {
                    Log.e(TAG, sqlEx.getMessage(), sqlEx);
                }
                jobFinished(params, true);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}