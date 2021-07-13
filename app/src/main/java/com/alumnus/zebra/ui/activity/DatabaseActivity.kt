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
import com.alumnus.zebra.utils.Constant;
import com.alumnus.zebra.utils.DateFormatter;
import com.alumnus.zebra.utils.ZipManager;

import java.io.File;
import java.util.List;


/**
 * Contains info about available data in Database accLog table
 * &
 * Exported .csv files available in storage @ZebraApp folder
 *
 * @author Arnab Kundu
 */
public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    TextView tv_db_record_count_event_table, tv_db_record_count_acc_table, tv_csv_list_table;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        tv_db_record_count_event_table = findViewById(R.id.tv_db_record_count_event_table);
        tv_db_record_count_acc_table = findViewById(R.id.tv_db_record_count_acc_table);
        tv_csv_list_table = findViewById(R.id.tv_csv_list_table);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
    }

    public void getTotalCount(View view) {
        new DBTask().execute();
    }

    public void getLastTimeStamp(View v) {
        new FetchTimeStampDBTask().execute();
    }

    public void getCsvListTable(View view) {
        new CsvFileDBTask().execute();
        Toast.makeText(this, "Chunk size: " + Constant.DATA_CHUNK_SIZE + "\nRetain files count: " + Constant.RETAIN_NUMBER_OF_CSV_FILE, Toast.LENGTH_SHORT).show();
    }

    /**
     * Suspended function. Must be running in background Thread always.
     * @param listOfCsv ArrayList of {@link CsvFileLogEntity}
     */
    private void zipAllCSVFiles(List<CsvFileLogEntity> listOfCsv) {
        String[] s = new String[listOfCsv.size()];
        String filePath = "/storage/emulated/0/ZebraApp/csvData/"; //TODO Android R filePath
        for (int row = 0; row < listOfCsv.size(); row++) {
            s[row] = filePath + listOfCsv.get(row).getFile_name() + ".csv";
        }

        // Check recoded file in Database is actually available in SD card or not.
        boolean isFilesAvailableForZipping = false;
        for (String stringFilePath : s) {
            File f = new File(stringFilePath);
            if (f.exists()) {
                isFilesAvailableForZipping = true;
                break;
            }
        }

        // Zip and delete available csvFiles
        if (isFilesAvailableForZipping) {
            ZipManager zipManager = new ZipManager();
            zipManager.zip(s, null);
            for (String stringFilePath : s) {
                File f = new File(stringFilePath);
                f.delete();
            }
        }

        // Delete csvFiles record from DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        db.csvFileLogDao().deleteAllCSV();
    }

    public void archive(View view) {
        new ZipAndDeleteTask().execute();
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
            stringBuilder.append("|-----------------------------------------------|----------|\n");
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            List<CsvFileLogEntity> listOfCsv = db.csvFileLogDao().getAll();
            for (int row = 0; row < listOfCsv.size(); row++) {
                stringBuilder.append(String.format("| %20s | %5s |\n", listOfCsv.get(row).getFile_name(), listOfCsv.get(row).getCount()));
            }

            System.out.println(stringBuilder.toString());
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv_csv_list_table.setText(s);
        }
    }

    class ZipAndDeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            List<CsvFileLogEntity> listOfCsv = db.csvFileLogDao().getAll();

            if (listOfCsv.size() > 0) {             // Avoid creating 0 byte Zip file, When no csvFile available.
                /** Zip CSV files and delete the original .csv files */
                zipAllCSVFiles(listOfCsv);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(DatabaseActivity.this, "Zip successful", Toast.LENGTH_LONG).show();
        }
    }
}