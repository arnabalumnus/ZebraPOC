package com.example.zebrapoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
}