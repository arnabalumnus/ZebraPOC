package com.example.zebrapoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.example.zebrapoc.R;
import com.example.zebrapoc.db.AppDatabase;
import com.example.zebrapoc.db.EventLogEntity;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }


    public void getAllEvent(View view) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        Runnable runnable = () -> db.eventLogDao().getAll();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}