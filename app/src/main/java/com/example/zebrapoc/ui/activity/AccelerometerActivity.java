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

import static com.example.zebrapoc.utils.Constant.G_CALIBRATION_FACTOR;
import static com.example.zebrapoc.utils.Constant.THROW_CALIBRATION_FACTOR;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener, OnChartValueSelectedListener {

    private final String TAG = this.getClass().getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private LineChart xyzChart, tsvChart;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        textView = findViewById(R.id.textView);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        xyzChart = findViewById(R.id.chart_xyz);
        tsvChart = findViewById(R.id.chart_tsv);
        setXYZGraph(xyzChart);
        setTSVGraph(tsvChart);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private void setXYZGraph(LineChart chart) {

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

    private void setTSVGraph(LineChart chart) {

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
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void addEntry(float x, float y, float z) {

        LineData data = xyzChart.getData();

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
            xyzChart.notifyDataSetChanged();

            // limit the number of visible entries
            xyzChart.setVisibleXRangeMaximum(20);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            xyzChart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // chart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private void addTSVEntry(float tsv) {

        LineData data = tsvChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);

            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet("TSV");
                data.addDataSet(set);
            }

            data.addEntry(new Entry(set.getEntryCount(), tsv), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            tsvChart.notifyDataSetChanged();

            // limit the number of visible entries
            tsvChart.setVisibleXRangeMaximum(20);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            tsvChart.moveViewToX(data.getEntryCount());

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
        if (axis.equalsIgnoreCase("TSV")) {
            set = new LineDataSet(null, "TSV Data");
            set.setColor(ColorTemplate.getHoloBlue());
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

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
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

        double TSV = Math.sqrt((G_towards_X * G_towards_X) + (G_towards_Y * G_towards_Y) + (G_towards_Z * G_towards_Z));
        addTSVEntry((float) TSV);

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