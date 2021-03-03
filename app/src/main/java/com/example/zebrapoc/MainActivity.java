package com.example.zebrapoc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.EventLogEntity;
import com.example.zebrapoc.utils.DateFormatter;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

    public void exportData(View view) {
        Runnable runnable = () -> {
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");
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
                Toast.makeText(this, "Exported", Toast.LENGTH_LONG).show();
            } catch (Exception sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}