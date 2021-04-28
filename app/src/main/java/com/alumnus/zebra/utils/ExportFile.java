package com.alumnus.zebra.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.room.Room;

import com.alumnus.zebra.db.AppDatabase;
import com.alumnus.zebra.db.entity.AccLogEntity;
import com.alumnus.zebra.db.entity.CsvFileLogEntity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ExportFile {
    private static final String TAG = "ExportFile";

    /**
     * @param context    context required for file saving
     * @param exportType folder name will be create based on this params (1. manualLog, 2. autoLog, 3. onPowerConnect)
     */
    public static void exportDataIntoCSVFile(Context context, String exportType) {
        Runnable runnable = () -> {
            long delete_upto_time_stamp = System.currentTimeMillis();
            File exportDir;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) // Saves file inside root/ZebraApp
                exportDir = new File(Environment.getExternalStorageDirectory(), "ZebraApp/" + exportType); // Working in API 29 i.e. Android 10 and lower
            else // Save file inside root/Android/data/com.alumnus.zebra/files/ZebraApp
                exportDir = new File(context.getExternalFilesDir("ZebraApp"), exportType); // Working in API 30 i.e. Android 11 and higher
            if (!exportDir.exists()) {
                if (!exportDir.mkdirs()) {
                    Log.e(TAG, "Error in mkdirs");
                    return;
                }
            }
            AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-name").build();
            List<AccLogEntity> accLogEntities = db.accLogDao().getAll();
            if (accLogEntities.size() == 0) {
                Log.w(TAG, "No data available in database");
                return;
            }
            File file = new File(exportDir, DateFormatter.getTimeStampFileName(System.currentTimeMillis()) + ".csv");
            try {
                if (!file.createNewFile()) {
                    Log.e(TAG, "Error in createNewFile");
                    return;
                }
                db.csvFileLogDao().insert(new CsvFileLogEntity(file.getName(), accLogEntities.size()));
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                csvWrite.writeNext(new String[]{"TS", "X", "Y", "Z"});
                for (AccLogEntity accLogEntity : accLogEntities) {
                    //Which column you want to export
                    String[] arrStr = {String.valueOf(accLogEntity.ts), String.valueOf(accLogEntity.x), String.valueOf(accLogEntity.y), String.valueOf(accLogEntity.z)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                Log.d(TAG, "Data Exported");
                Thread.sleep(300);
                db.accLogDao().deleteAll(delete_upto_time_stamp);
                MediaScannerConnection.scanFile(context,
                        new String[]{file.toString()},
                        null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i(TAG, "onScanCompleted: path: " + path);
                                Log.v(TAG, "onScanCompleted: Uri: " + uri);
                            }
                        });
                if (db.csvFileLogDao().getTotalRecordOfAllCSVFile() > 18000 && db.csvFileLogDao().getCSVFileCount() > 2) {//432000
                    DeleteFile.deleteFile(context, exportType);
                }
            } catch (Exception sqlEx) {
                Log.e(TAG, sqlEx.getMessage(), sqlEx);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
