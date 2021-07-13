package com.alumnus.zebra.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alumnus.zebra.BuildConfig
import com.alumnus.zebra.R
import com.alumnus.zebra.machineLearning.DataAnalysis
import com.alumnus.zebra.machineLearning.RFClassifierForImpactData
import com.alumnus.zebra.machineLearning.RandomForestClassifier
import com.alumnus.zebra.pojo.AccelerationNumericData
import com.alumnus.zebra.pojo.AccelerationStringData
import com.alumnus.zebra.ui.adapter.AccelerationDataAdapter
import com.alumnus.zebra.utils.CsvFileOperator.readCsvFile
import kotlinx.android.synthetic.main.activity_csv_explorer.*
import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * This activity responsible for opening .csv files exported by Zebra app
 *
 * @author Arnab Kundu
 */
class CsvExplorerActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_csv_explorer)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_acceleration_data.layoutManager = linearLayoutManager
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data // Get File data from Intent
        val file = File(uri!!.path!!)
        val fileName = file.name // Get File name from uri
        Log.i(TAG, "FileName: $fileName")
        try {
            val inputStream = contentResolver.openInputStream(uri) // Convert received intent data into InputStream.

            // Convert inputStream to ArrayList
            val accelerations = readCsvFile(inputStream!!)
            Toast.makeText(this, "Row count: " + accelerations.size, Toast.LENGTH_SHORT).show()

            // Feed adapter with data
            val accelerationDataAdapter = AccelerationDataAdapter(accelerations)
            rv_acceleration_data!!.adapter = accelerationDataAdapter
            if (BuildConfig.DEBUG) generateLogFile(accelerations)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    /**
     * Generate corresponding Log file on opening CSV data file
     *
     * @param dataList Array:ist of AccelerationStringData
     */
    private fun generateLogFile(dataList: ArrayList<AccelerationStringData>) {
        /* Acceleration Numeric data collection */
        val accNumericDataList = ArrayList<AccelerationNumericData>()
        val accNumericData = AccelerationNumericData()

        //Skip header
        dataList.removeAt(0)
        for ((ts, x, y, z) in dataList) {
            accNumericData.ts = ts.toLong()
            accNumericData.x = x.toFloat()
            accNumericData.y = y.toFloat()
            accNumericData.z = z.toFloat()
            accNumericDataList.add(accNumericData)
        }
        val result = DataAnalysis().startEventAnalysis(accNumericDataList, this, null)
        Log.i(TAG, "LogFile: $result")
        val data = DoubleArray(accNumericDataList.size * 3)
        var count = 0
        while (count < data.size) {
            data[count] = accNumericDataList[count++ / 3].x.toDouble()
            data[count] = accNumericDataList[count++ / 3].y.toDouble()
            data[count] = accNumericDataList[count++ / 3].z.toDouble()
        }
        val predictedFallResult = RandomForestClassifier.predict(data)
        val predictedImpactResult = RFClassifierForImpactData.predict(data)
        Log.i(TAG, "Predicted:")
        Log.i(TAG, "Fall Result: $predictedFallResult")
        Log.i(TAG, "Impact Result: $predictedImpactResult")
        Log.i(TAG, "=======================================================================================")
        Toast.makeText(this, "$result\nPredicted\nFall Result: $predictedFallResult\nImpact Result: $predictedImpactResult", Toast.LENGTH_SHORT).show()
    }

}