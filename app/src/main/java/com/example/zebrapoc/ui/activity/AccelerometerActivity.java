package com.example.zebrapoc.ui.activity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.entity.AccLogEntity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static com.example.zebrapoc.utils.Constant.*;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener, OnChartValueSelectedListener {

    private final String TAG = this.getClass().getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    Queue<AccLogEntity> accLogEntityQueue = new ArrayDeque<>();
    private LineChart chart;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        textView = findViewById(R.id.textView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        serGraph();
        //feedMultiple();
    }

    private void serGraph() {

        chart = findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);

        // enable description text
        chart.getDescription().setEnabled(true);
        chart.getDescription().setText("Accelerometer data");

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.LTGRAY);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        //l.setTypeface(tfLight);
        l.setTextColor(Color.WHITE);

        XAxis xl = chart.getXAxis();
        //xl.setTypeface(tfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaximum(20f);
        leftAxis.setAxisMinimum(-20f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void addEntry(float x, float y, float z) {

        LineData data = chart.getData();

        if (data != null) {

            ILineDataSet setx = data.getDataSetByIndex(0);
            ILineDataSet sety = data.getDataSetByIndex(1);
            ILineDataSet setz = data.getDataSetByIndex(2);
            // set.addEntry(...); // can be called as well

            if (setx == null) {
                setx = createSet("X");
                data.addDataSet(setx);
            }

            if (sety == null) {
                sety = createSet("Y");
                data.addDataSet(sety);
            }

            if (setz == null) {
                setz = createSet("Z");
                data.addDataSet(setz);
            }

            data.addEntry(new Entry(setx.getEntryCount(), x), 0);
            data.addEntry(new Entry(sety.getEntryCount(), y), 1);
            data.addEntry(new Entry(setz.getEntryCount(), z), 2);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            chart.notifyDataSetChanged();

            // limit the number of visible entries
            chart.setVisibleXRangeMaximum(20);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // chart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private LineDataSet createSet(String axis) {

        LineDataSet set = null;
        if (axis.equalsIgnoreCase("X")) {
            set = new LineDataSet(null, "X Data");
            set.setColor(ColorTemplate.MATERIAL_COLORS[0]);
        }
        if (axis.equalsIgnoreCase("Y")) {
            set = new LineDataSet(null, "Y Data");
            set.setColor(ColorTemplate.MATERIAL_COLORS[2]);
        }
        if (axis.equalsIgnoreCase("Z")) {
            set = new LineDataSet(null, "Z Data");
            set.setColor(ColorTemplate.MATERIAL_COLORS[3]);
        }
        if (set != null) {
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            set.setCircleColor(Color.WHITE);
            set.setLineWidth(2f);
            set.setCircleRadius(4f);
            set.setFillAlpha(65);
            set.setFillColor(ColorTemplate.getHoloBlue());
            set.setHighLightColor(Color.rgb(244, 117, 117));
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(9f);
            set.setDrawValues(false);
        }
        return set;
    }

    private Thread thread;

    private void feedMultiple() {

        if (thread != null)
            thread.interrupt();

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                //addEntry(0);
            }
        };

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {

                    // Don't generate garbage runnables inside the loop.
                    runOnUiThread(runnable);

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    private void setData(Queue<AccLogEntity> queue) {

        ArrayList<Entry> values1 = new ArrayList<>();

      /*  for (int i = 0; i < queue.size(); i++) {
            float val = (float) (Math.random() * (range / 2f)) + 50;
            values1.add(new Entry(i, val));
        }
      */


        ArrayList<Entry> values2 = new ArrayList<>();

     /*   for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 450;
            values2.add(new Entry(i, val));
        }
*/
        ArrayList<Entry> values3 = new ArrayList<>();
/*
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 500;
            values3.add(new Entry(i, val));
        }*/
        int count = 0;
        for (AccLogEntity item : queue) {
            values1.add(new Entry(count, item.x));
            values2.add(new Entry(count, item.y));
            values3.add(new Entry(count, item.z));
            count++;
        }

        LineDataSet set1, set2, set3;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) chart.getData().getDataSetByIndex(2);
            set1.setValues(values1);
            set2.setValues(values2);
            set3.setValues(values3);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set3 = new LineDataSet(values3, "DataSet 3");
            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // create a data object with the data sets
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            chart.setData(data);
        }
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

        addEntry(G_towards_X, G_towards_Y, G_towards_Z);
        accLogEntityQueue.add(new AccLogEntity(System.currentTimeMillis(), G_towards_X, G_towards_Y, G_towards_Z));
       /* if(accLogEntityQueue.size()>20){
            accLogEntityQueue.remove();
        setData(accLogEntityQueue);
        }*/
        if (accLogEntityQueue.size() == 20) {

        }
        //float total_G = event.values[0] + event.values[1] + event.values[2];
        //Log.d(TAG, "onSensorChanged: Total: " + total_G);
        if (Math.abs(G_towards_X) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Y) < G_CALIBRATION_FACTOR && Math.abs(G_towards_Z) < G_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a free fall");
            textView.setText("It's a free fall");
        }

        if (Math.abs(G_towards_X) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Y) > THROW_CALIBRATION_FACTOR || Math.abs(G_towards_Z) > THROW_CALIBRATION_FACTOR) {
            Log.e(TAG, "It's a throw");
            textView.setText("It's a throw");
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}