package com.example.zebrapoc;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    /**
     * G_calibration_factor value need to decrease gradually to be more accurate about a free fall
     */
    private static final float G_CALIBRATION_FACTOR = 5.89F; //value need to decrease gradually to be more accurate about a free fall

    /**
     * throw_calibration_factor value need to increase gradually to be more accurate about a throw
     */
    private static final float THROW_CALIBRATION_FACTOR = 9.81F; //value need to decrease gradually to be more accurate about a free fall


    private final String TAG = this.getClass().getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        textView = findViewById(R.id.textView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        for (float i : event.values) {
            //Log.d(TAG, "onSensorChanged: "+i);
        }

        //Log.d(TAG, "onSensorChanged: Name: "+event.sensor.getName());
        //Log.d(TAG, "onSensorChanged: StringType: "+event.sensor.getStringType());
        //Log.d(TAG, "onSensorChanged: Vendor: "+event.sensor.getVendor());
        //Log.d(TAG, "onSensorChanged: Power: "+event.sensor.getPower());

        //Log.d(TAG, "onSensorChanged: X: " + event.values[0]);
        //Log.d(TAG, "onSensorChanged: Y: " + event.values[1]);
        //Log.d(TAG, "onSensorChanged: Z: " + event.values[2]);

        float G_towards_X = event.values[0];
        float G_towards_Y = event.values[1];
        float G_towards_Z = event.values[2];


        float total_G = event.values[0] + event.values[1] + event.values[2];
        Log.d(TAG, "onSensorChanged: Total: " + total_G);
        if (Math.abs(G_towards_X) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Y) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Z) < G_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a free fall");
            textView.setText("It's a free fall");
        }

        if (Math.abs(G_towards_X) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Y) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Z) > THROW_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a throw");
            textView.setText("It's a throw");
        }
    }
}