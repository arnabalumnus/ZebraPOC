package com.alumnus.zebra.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.alumnus.zebra.R;
import com.alumnus.zebra.db.AppDatabase;
import com.alumnus.zebra.db.entity.CsvFileLogEntity;
import com.alumnus.zebra.utils.DateFormatter;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    TextView tv_db_record_count_event_table, tv_db_record_count_acc_table, tv_db_record_count_log_table;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        tv_db_record_count_event_table = findViewById(R.id.tv_db_record_count_event_table);
        tv_db_record_count_acc_table = findViewById(R.id.tv_db_record_count_acc_table);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
    }

    public void getAllEvent(View view) {
        Runnable runnable = () -> db.eventLogDao().getAll();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void getTotalCount(View view) {
        new DBTask().execute();
    }

    public void getLastTimeStamp(View v) {
        new FetchTimeStampDBTask().execute();
        new CsvFileDBTask().execute();
    }

    class DBTask extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... voids) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

            long accCount = db.accLogDao().getCount();
            return accCount;
        }

        @Override
        protected void onPostExecute(Long count) {
            super.onPostExecute(count);
            tv_db_record_count_acc_table.setText("Accelerometer table row count: " + count);
        }
    }

    class FetchTimeStampDBTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            long startingTimeStamp = db.accLogDao().getStartingTimeStamp();
            long lastRecordTime = db.accLogDao().getLastRecordTime();
            return "Starting timestamp: " + DateFormatter.getTimeStamp(startingTimeStamp) +
                    "\nLast timestamp: " + DateFormatter.getTimeStamp(lastRecordTime);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv_db_record_count_event_table.setText(s);
        }
    }

    class CsvFileDBTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("| FileName                                  |  rows |\n");
            stringBuilder.append("|-----------------------------------------------|---------|\n");
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            List<CsvFileLogEntity> listOfCsv = db.csvFileLogDao().getAll();
            for (int row = 0; row < listOfCsv.size() / 3; row++) {
                stringBuilder.append(String.format("| %20s | %5s |\n", listOfCsv.get(row).file_name, listOfCsv.get(row).count));
                //System.out.println(String.format("%12s | %20s | %12s", listOfCsv.get(row).id, listOfCsv.get(row).file_name, listOfCsv.get(row).count));
            }
            System.out.println(stringBuilder.toString());
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(DatabaseActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}