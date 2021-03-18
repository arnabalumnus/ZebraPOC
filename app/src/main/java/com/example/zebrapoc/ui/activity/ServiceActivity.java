package com.example.zebrapoc.ui.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zebrapoc.R;
import com.example.zebrapoc.service.LifeTimeService;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "ServiceActivity";
    int frequency = 50; //Records per second from accelerometer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        RadioGroup frequency_radio_group = findViewById(R.id.frequency_radio_group);
        frequency_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.low_freq:
                        frequency = 5;
                        Log.d(TAG, "low_freq: ");
                        break;
                    case R.id.mid_freq:
                        frequency = 15;
                        Log.d(TAG, "mid_freq: ");
                        break;
                    case R.id.high_freq:
                        frequency = 50;
                        Log.d(TAG, "high_freq: ");
                        break;
                    default:
                        Log.d(TAG, "default: ");
                }
            }
        });
    }

    public void startLifeTimeService(View v) {
        Intent intent = new Intent(this, LifeTimeService.class);
        intent.putExtra("frequency", frequency);
        startForegroundService(intent);

        Intent myIntent = new Intent(this, LifeTimeService.class);
        myIntent.putExtra("ALARM", true);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        /**
         AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
         long firstTime = SystemClock.elapsedRealtime();
         alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 5 * 60 * 1000, pendingIntent);
         */
    }
}