package com.alumnus.zebra.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alumnus.zebra.BuildConfig;
import com.alumnus.zebra.R;
import com.alumnus.zebra.machineLearning.DataAnalysis;
import com.alumnus.zebra.machineLearning.RFClassifierForImpactData;
import com.alumnus.zebra.machineLearning.RandomForestClassifier;
import com.alumnus.zebra.pojo.AccelerationNumericData;
import com.alumnus.zebra.pojo.AccelerationStringData;
import com.alumnus.zebra.ui.adapter.AccelerationDataAdapter;
import com.alumnus.zebra.utils.CsvFileOperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * This activity responsible for opening .csv files exported by Zebra app
 *
 * @author Arnab Kundu
 */
public class CsvExplorerActivity extends AppCompatActivity {
    private static final String TAG = "CsvExplorerActivity";
    private RecyclerView rv_acceleration_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_csv_explorer);

        rv_acceleration_data = findViewById(R.id.rv_acceleration_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_acceleration_data.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();                                                // Get File data from Intent
        File file = new File(uri.getPath());
        String fileName = file.getName();                                               // Get File name from uri
        Log.i(TAG, "FileName: " + fileName);
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);        // Convert received intent data into InputStream.

            // Convert inputStream to ArrayList
            ArrayList<AccelerationStringData> accelerations = CsvFileOperator.INSTANCE.readCsvFile(inputStream);
            Toast.makeText(this, "Row count: " + accelerations.size(), Toast.LENGTH_SHORT).show();

            // Feed adapter with data
            AccelerationDataAdapter accelerationDataAdapter = new AccelerationDataAdapter(accelerations);
            rv_acceleration_data.setAdapter(accelerationDataAdapter);

            if (BuildConfig.DEBUG)
                generateLogFile(accelerations);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generate corresponding Log file on opening CSV data file
     *
     * @param dataList Array:ist of AccelerationStringData
     */
    private void generateLogFile(ArrayList<AccelerationStringData> dataList) {
        /* Acceleration Numeric data collection */
        ArrayList<AccelerationNumericData> accNumericDataList = new ArrayList<>();
        AccelerationNumericData accNumericData = new AccelerationNumericData();

        //Skip header
        dataList.remove(0);

        for (AccelerationStringData accStringData : dataList) {
            accNumericData.setTs(Long.parseLong(accStringData.getTs()));
            accNumericData.setX(Float.parseFloat(accStringData.getX()));
            accNumericData.setY(Float.parseFloat(accStringData.getY()));
            accNumericData.setZ(Float.parseFloat(accStringData.getZ()));
            accNumericDataList.add(accNumericData);
        }
        String result = new DataAnalysis().startEventAnalysis(accNumericDataList, this, null);
        Log.i(TAG, "LogFile: " + result);

        double[] data = new double[accNumericDataList.size() * 3];
        int count = 0;
        while (count < data.length) {
            data[count] = accNumericDataList.get(count++ / 3).getX();
            data[count] = accNumericDataList.get(count++ / 3).getY();
            data[count] = accNumericDataList.get(count++ / 3).getZ();
        }

        int predictedFallResult = RandomForestClassifier.predict(data);
        int predictedImpactResult = RFClassifierForImpactData.predict(data);
        Log.i(TAG, "Predicted:");
        Log.i(TAG, "Fall Result: " + predictedFallResult);
        Log.i(TAG, "Impact Result: " + predictedImpactResult);
        Log.i(TAG, "=======================================================================================");
        Toast.makeText(this, result + "\nPredicted\nFall Result: " + predictedFallResult + "\nImpact Result: " + predictedImpactResult, Toast.LENGTH_SHORT).show();
    }
}
