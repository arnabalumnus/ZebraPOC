package com.alumnus.zebra.ui.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alumnus.zebra.R;
import com.alumnus.zebra.service.LifeTimeService;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "ServiceActivity";
    int frequency = 50; //Records per second from accelerometer
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        sp = getSharedPreferences("Zebra", MODE_PRIVATE);
        int freqId = sp.getInt("frequencyId", 0);

        RadioGroup frequency_radio_group = findViewById(R.id.frequency_radio_group);
        if (freqId != 0) {
            frequency_radio_group.check(freqId);
        } else {
            Toast.makeText(this, "Frequency not selected", Toast.LENGTH_SHORT).show();
        }
        frequency_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor = sp.edit();
                switch (checkedId) {
                    case R.id.low_freq:
                        frequency = 5;
                        Log.d(TAG, "low_freq: ");
                        editor.putInt("frequencyId", R.id.low_freq);
                        editor.putInt("frequency", 5);
                        break;
                    case R.id.mid_freq:
                        frequency = 15;
                        Log.d(TAG, "mid_freq: ");
                        editor.putInt("frequencyId", R.id.mid_freq);
                        editor.putInt("frequency", 15);
                        break;
                    case R.id.high_freq:
                        frequency = 50;
                        Log.d(TAG, "high_freq: ");
                        editor.putInt("frequencyId", R.id.high_freq);
                        editor.putInt("frequency", 50);
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
        if (editor != null)
            editor.apply();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(intent);
        else
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
}