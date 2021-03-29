package com.alumnus.zebra.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alumnus.zebra.R;
import com.alumnus.zebra.pojo.Acceleration;
import com.alumnus.zebra.ui.adapter.AccelerationDataAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CsvExplorerActivity extends AppCompatActivity {

    private ArrayList<Acceleration> accelerations = new ArrayList<>();
    private AccelerationDataAdapter accelerationDataAdapter;
    private RecyclerView rv_acceleration_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_explorer);

        rv_acceleration_data = findViewById(R.id.rv_acceleration_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_acceleration_data.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            readWeatherData(inputStream);                  // If you need to read the whole file row by row
            //readWeatherDataByColumn();        // If you need to read specific column row by row

            accelerationDataAdapter = new AccelerationDataAdapter(accelerations);
            rv_acceleration_data.setAdapter(accelerationDataAdapter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param is
     */
    private void readWeatherDataByColumn(InputStream is) {
        // Read the raw csv file

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Initialization
        String line = "";

        // Handling exceptions
        try {
            // If buffer is not empty
            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");

                // Print in logcat
                System.out.println("Coulmn 0 = '" + cols[0] + "', Column 1 = '" + cols[1] + "', Column 2: '" + cols[2] + "'");
            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }
    }

    /**
     * @param is
     */
    private void readWeatherData(InputStream is) {
        // Read the raw csv file

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        // Initialization
        String line = "";

        // Initialization
        try {
            // Step over headers
            //reader.readLine();

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data

               /* AccLogEntity accLogEntity = new AccLogEntity();
                // Setters
                accLogEntity.setTs(Long.parseLong(tokens[0]));
                accLogEntity.setX(Float.parseFloat(tokens[1]));
                accLogEntity.setY(Float.parseFloat(tokens[2]));
                accLogEntity.setZ(Float.parseFloat(tokens[3]));

                // Adding object to a class
                accLogEntities.add(accLogEntity);

                // Log the object
                Log.d("My Activity", "Just created: " + accLogEntity);*/

                Acceleration acceleration = new Acceleration(tokens[0].replace("\"", ""),
                        tokens[1].replace("\"", ""),
                        tokens[2].replace("\"", ""),
                        tokens[3].replace("\"", ""));
                accelerations.add(acceleration);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
    }
}
