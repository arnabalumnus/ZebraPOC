package com.alumnus.zebra.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.room.Room;

import com.alumnus.zebra.db.AppDatabase;
import com.alumnus.zebra.db.entity.AccLogEntity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ExportFile {
    private static final String TAG = "ExportFile";

    public static void exportDataIntoCSVFile(Context context, String exportType) {
        Runnable runnable = () -> {
            long delete_upto_time_stamp = System.currentTimeMillis();
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
                Thread.sleep(1000);
                db.accLogDao().deleteAll(delete_upto_time_stamp);
            } catch (Exception sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
