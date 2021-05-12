package com.alumnus.zebra.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alumnus.zebra.BuildConfig;
import com.alumnus.zebra.R;
import com.alumnus.zebra.machineLearning.DataAnalysis;
import com.alumnus.zebra.pojo.AccelerationNumericData;
import com.alumnus.zebra.pojo.AccelerationStringData;
import com.alumnus.zebra.ui.adapter.AccelerationDataAdapter;
import com.alumnus.zebra.utils.CsvFileOperator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * This activity responsible for opening .csv files exported by Zebra app
 *
 * @author Arnab Kundu
 */
public class CsvExplorerActivity extends AppCompatActivity {

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
            accNumericData.setTs(Long.parseLong(accStringData.ts));
            accNumericData.setX(Float.parseFloat(accStringData.x));
            accNumericData.setY(Float.parseFloat(accStringData.y));
            accNumericData.setZ(Float.parseFloat(accStringData.z));
            accNumericDataList.add(accNumericData);
        }
        String result = new DataAnalysis().startEventAnalysis(accNumericDataList, this, null);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
