package com.alumnus.zebra.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.alumnus.zebra.R;
import com.alumnus.zebra.db.AppDatabase;
import com.alumnus.zebra.utils.DateFormatter;

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
        tv_db_record_count_log_table = findViewById(R.id.tv_db_record_count_log_table);
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

    class DBTask extends AsyncTask<Void, Void, Long[]> {

        @Override
        protected Long[] doInBackground(Void... voids) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            Long[] count = {0L, 0L, 0L};
            long eventCount = db.eventLogDao().getCount();
            long accCount = db.accLogDao().getCount();
            long logCount = db.logDao().getCount();
            count[0] = eventCount;
            count[1] = accCount;
            count[2] = logCount;
            return count;
        }

        @Override
        protected void onPostExecute(Long[] count) {
            super.onPostExecute(count);
            tv_db_record_count_event_table.setText("Event Count: " + count[0]);
            tv_db_record_count_acc_table.setText("Acc Count: " + count[1]);
            tv_db_record_count_log_table.setText("Log Count: " + count[2]);
        }
    }

    class FetchTimeStampDBTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            long[] firstLastRecordTime= db.accLogDao().getLastRecordTime();
            return "Last: " + DateFormatter.getTimeStamp(firstLastRecordTime[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv_db_record_count_event_table.setText(s);
        }
    }

    public void getLastTimeStamp(View v) {
        new FetchTimeStampDBTask().execute();
    }
}