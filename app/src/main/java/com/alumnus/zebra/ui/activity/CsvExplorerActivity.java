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

import com.alumnus.zebra.R;
import com.alumnus.zebra.pojo.AccelerationNumericData;
import com.alumnus.zebra.pojo.AccelerationStringData;
import com.alumnus.zebra.ui.adapter.AccelerationDataAdapter;
import com.alumnus.zebra.utils.CsvFileOperator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * This activity responsible for opening .csv files exported by Zebra app
 *
 * @author Arnab Kundu
 */
public class CsvExplorerActivity extends AppCompatActivity {

    private static final String TAG = "CsvExplorerActivity";
    private ArrayList<AccelerationStringData> accelerations = new ArrayList<>();
    private ArrayList<AccelerationNumericData> accelerationsDataList = new ArrayList<>();
    private AccelerationDataAdapter accelerationDataAdapter;
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
            readCSVData(inputStream);                                                   // Read the whole file row by row

            //accelerations = CsvFileOperator.INSTANCE.readCsvFile(inputStream);        //TODO
            accelerationDataAdapter = new AccelerationDataAdapter(accelerations);
            rv_acceleration_data.setAdapter(accelerationDataAdapter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * From intentData uri & convert into inputStream
     *
     * @param is inputStream
     */
    private void readCSVData(InputStream is) {
        // Read the raw csv file

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        // Initialization
        String line = "";

        // Initialization
        try {
            /* Step over headers outside while loop as header contains non-numeric values. (Which is not required for calculation) */
            String header = reader.readLine();
            String[] headerTokens = header.split(",");
            // Read the data
            accelerations.add(
                    new AccelerationStringData(
                            headerTokens[0].replace("\"", ""),
                            headerTokens[1].replace("\"", ""),
                            headerTokens[2].replace("\"", ""),
                            headerTokens[3].replace("\"", "")
                    )
            );

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.v(TAG, "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data

                /* Acceleration String data collection */
                AccelerationStringData acceleration = new AccelerationStringData(
                        tokens[0].replace("\"", ""),
                        tokens[1].replace("\"", ""),
                        tokens[2].replace("\"", ""),
                        tokens[3].replace("\"", ""));
                accelerations.add(acceleration);

                /* Acceleration Numeric data collection */
                AccelerationNumericData accelerationData = new AccelerationNumericData(
                        Long.parseLong(tokens[0].replace("\"", "")),
                        Float.parseFloat(tokens[1].replace("\"", "")),
                        Float.parseFloat(tokens[2].replace("\"", "")),
                        Float.parseFloat(tokens[3].replace("\"", "")));
                accelerationsDataList.add(accelerationData);
            }

            Toast.makeText(this, "Row count: " + accelerations.size(), Toast.LENGTH_SHORT).show();

            /**
             // Uncomment this section if CSV file data need to be processed for logs file creation.
             String result = new MachineLearning().CalculateTSV(accelerationsDataList, this, null);
             Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
             */

        } catch (IOException e) {
            Log.e(TAG, "Error reading data file on line" + line, e);
        }
    }
}
