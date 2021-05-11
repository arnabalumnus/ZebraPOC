package com.alumnus.zebra.ui.activity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alumnus.zebra.R;
import com.alumnus.zebra.service.SaveCsvJobService;
import com.alumnus.zebra.utils.TimeSpan;


/**
 * Starts a JobService using JobScheduler ("Not using currently")
 *
 * @author Arnab Kundu
 */
@Deprecated
public class JobActivity extends AppCompatActivity {

    private static final String TAG = "JobActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
    }

    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(this, SaveCsvJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(false)
                //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setRequiresDeviceIdle(false)
                .setPeriodic(TimeSpan.SIX_HOUR)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}