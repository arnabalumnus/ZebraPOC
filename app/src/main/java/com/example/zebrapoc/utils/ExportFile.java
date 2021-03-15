package com.example.zebrapoc.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.room.Room;

import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.entity.AccLogEntity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ExportFile {
    private static final String TAG = "ExportFile";

    public static void exportDataIntoCSVFile(Context context, String exportType) {
        Runnable runnable = () -> {
            File exportDir;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                exportDir = new File(context.getExternalFilesDir("ZebraApp"), exportType); // Working in API 30 i.e. Android 11 and higher
            else
                exportDir = new File(Environment.getExternalStorageDirectory(), "ZebraApp/" + exportType); // Working in API 29 i.e. Android 10 and lower
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
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                List<AccLogEntity> accLogEntities = db.accLogDao().getAll();
                csvWrite.writeNext(new String[]{"TS", "X", "Y", "Z"});
                for (AccLogEntity accLogEntity : accLogEntities) {
                    //Which column you want to export
                    String[] arrStr = {String.valueOf(accLogEntity.ts), String.valueOf(accLogEntity.x), String.valueOf(accLogEntity.y), String.valueOf(accLogEntity.z)};
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
}