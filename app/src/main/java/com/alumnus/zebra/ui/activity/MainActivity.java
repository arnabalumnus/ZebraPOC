package com.alumnus.zebra.ui.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.alumnus.zebra.R;
import com.alumnus.zebra.broadcastReceiver.PowerConnectionReceiver;
import com.alumnus.zebra.broadcastReceiver.ServiceStopReceiver;
import com.alumnus.zebra.service.LifeTimeService;
import com.alumnus.zebra.utils.ExportFile;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int frequency = 50; //Records per second from accelerometer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isStoragePermissionGranted();

        PowerConnectionReceiver receiver = new PowerConnectionReceiver();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        iFilter.addAction(Intent.ACTION_BATTERY_LOW);
        iFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        registerReceiver(receiver, iFilter);
        //unregisterReceiver(receiver);

        ServiceStopReceiver serviceStopReceiver = new ServiceStopReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.LifeTimeService.stopped");
        registerReceiver(serviceStopReceiver, intentFilter);
    }

    public void goToAccelerometer(View view) {
        startActivity(new Intent(this, AccelerometerActivity.class));
    }


    public void startService(View v) {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    public void startLifeTimeService(View v) {
        Intent intent = new Intent(this, LifeTimeService.class);
        intent.putExtra("frequency", frequency);
        startService(intent);

        Intent myIntent = new Intent(this, LifeTimeService.class);
        myIntent.putExtra("ALARM", true);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        /**
         AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
         long firstTime = SystemClock.elapsedRealtime();
         alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 5 * 60 * 1000, pendingIntent);
         */
    }

    public void stopService(View v) {

    }

    public void navigateToDatabase(View view) {
        startActivity(new Intent(this, DatabaseActivity.class));
    }

    //region Export Data and save into SD card or Phone Storage
    public void exportDataButton(View view) {
        if (isStoragePermissionGranted()) {
            ExportFile.exportDataIntoCSVFile(this, "manualLog");
            Toast.makeText(this, "Data exported in 'ZebraApp/manualLog' folder", Toast.LENGTH_SHORT).show();
        }
    }

    public void navigateToJobActivity(View view) {
        startActivity(new Intent(this, JobActivity.class));
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            ExportFile.exportDataIntoCSVFile(this, "manualLog");
        }
    }
    //endregion
}