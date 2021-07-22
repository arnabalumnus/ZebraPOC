package com.alumnus.zebra

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alumnus.zebra.db.AppDatabase
import com.alumnus.zebra.db.entity.AccLogEntity
import com.alumnus.zebra.pojo.AccelerationNumericData
import com.alumnus.zebra.utils.CsvFileOperator
import com.alumnus.zebra.utils.DateFormatter
import com.alumnus.zebra.utils.FolderFiles
import kotlinx.android.synthetic.main.activity_specfice_event_type_recorder.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SpecificEventTypeRecorderActivity : AppCompatActivity(), SensorEventListener {

    private var db: AppDatabase? = null
    private var accLogEntity: AccLogEntity? = null
    private var G_towards_X = 0f
    private var G_towards_Y = 0f
    private var G_towards_Z = 0f
    lateinit var job: Job

    private var trackingUpToTime: Long = 0
    private lateinit var fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specfice_event_type_recorder)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").allowMainThreadQueries().build()
        accLogEntity = AccLogEntity()
    }

    /**
     * Button Click
     */
    fun startTracking(view: View) {
        btn_start_tracking.isEnabled = false
        val mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW)
        trackingUpToTime = System.currentTimeMillis() + et_time_span.text.toString().toLong() * 1000 ?: 0
        fileName = et_event_type.text.toString() ?: "UndefinedFileName"
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (System.currentTimeMillis() > trackingUpToTime) {
            fetchRecordFromDBAndExportIntoCsvFile(fileName)
            val mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            mSensorManager.unregisterListener(this)
            return
        }

        try {
            G_towards_X = event.values[0]
            G_towards_Y = event.values[1]
            G_towards_Z = event.values[2]
            //Log.i(TAG, "onSensorChanged: X:$G_towards_X  Y:$G_towards_Y  Z:$G_towards_Z");
            accLogEntity!!.ts = System.currentTimeMillis()
            accLogEntity!!.x = G_towards_X
            accLogEntity!!.y = G_towards_Y
            accLogEntity!!.z = G_towards_Z

            if (db != null) {
                job = CoroutineScope(Dispatchers.IO).launch {
                    db!!.accLogDao().insert(accLogEntity!!)
                }
            }
        } catch (e: Exception) {
            //Log.e(TAG, "${e.message}")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // TODO("Not yet implemented")
    }

    private fun fetchRecordFromDBAndExportIntoCsvFile(fileName: String) {
        val accLogEntities: List<AccLogEntity> = db!!.accLogDao().all
        val accelerationsDataList: ArrayList<AccelerationNumericData> = ArrayList()
        for (accLogEntity in accLogEntities) {
            accelerationsDataList.add(AccelerationNumericData(accLogEntity.ts, accLogEntity.x, accLogEntity.y, accLogEntity.z))
        }
        FolderFiles.createFolder(this,"KnownTypesEvent")
        CsvFileOperator.writeCsvFile(this, accelerationsDataList, folderName = "KnownTypesEvent", fileName = "$fileName-${DateFormatter.getTimeStampFileName(System.currentTimeMillis())}")
        db!!.accLogDao().deleteAll(System.currentTimeMillis())
        Log.d("msg", "fetchRecordFromDBAndExportIntoCsvFile: $fileName ${DateFormatter.getTimeStampFileName(System.currentTimeMillis())}")
        btn_start_tracking.isEnabled = true
    }
}