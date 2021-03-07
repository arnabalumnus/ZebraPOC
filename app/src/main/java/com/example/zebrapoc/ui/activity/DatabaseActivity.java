package com.example.zebrapoc.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    TextView tv_db_record_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        tv_db_record_count = findViewById(R.id.tv_db_record_count);
    }


    public void getAllEvent(View view) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        Runnable runnable = () -> db.eventLogDao().getAll();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void getTotalCount(View view) {
        /*AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long count = db.eventLogDao().getCount();
                //Toast.makeText(DatabaseActivity.this, "Count: " + count, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "run: count:" + count);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();*/
        new DBTask().execute();
    }

    class DBTask extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
            long count = db.eventLogDao().getCount();
            return count;
        }

        @Override
        protected void onPostExecute(Long count) {
            super.onPostExecute(count);
            tv_db_record_count.setText("Count: " + count);
        }
    }
}