package com.alumnus.zebra.ui.activity

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alumnus.zebra.R
import com.alumnus.zebra.db.AppDatabase
import com.alumnus.zebra.db.entity.CsvFileLogEntity
import com.alumnus.zebra.utils.Constant
import com.alumnus.zebra.utils.DateFormatter
import com.alumnus.zebra.utils.ZipManager
import java.io.File

/**
 * Contains info about available data in Database accLog table
 * &
 * Exported .csv files available in storage @ZebraApp folder
 *
 * @author Arnab Kundu
 */
class DatabaseActivity : AppCompatActivity() {
    var tv_db_record_count_event_table: TextView? = null
    var tv_db_record_count_acc_table: TextView? = null
    var tv_csv_list_table: TextView? = null
    private var db: AppDatabase? = null

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(R.layout.activity_database)
        tv_db_record_count_event_table = findViewById(R.id.tv_db_record_count_event_table)
        tv_db_record_count_acc_table = findViewById(R.id.tv_db_record_count_acc_table)
        tv_csv_list_table = findViewById(R.id.tv_csv_list_table)
    }

    override fun onResume() {
        super.onResume()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
    }

    fun getTotalCount(view: View?) {
        DBTask().execute()
    }

    fun getLastTimeStamp(v: View?) {
        FetchTimeStampDBTask().execute()
    }

    fun getCsvListTable(view: View?) {
        CsvFileDBTask().execute()
        Toast.makeText(this, "Chunk size: ${Constant.DATA_CHUNK_SIZE} \nRetain files count: ${Constant.RETAIN_NUMBER_OF_CSV_FILE}", Toast.LENGTH_SHORT).show()
    }

    /**
     * Suspended function. Must be running in background Thread always.
     * @param listOfCsv ArrayList of [CsvFileLogEntity]
     */
    private fun zipAllCSVFiles(listOfCsv: List<CsvFileLogEntity>) {
        val s = arrayOf(String())
        val filePath = "/storage/emulated/0/ZebraApp/csvData/" //TODO Android R filePath
        for (row in listOfCsv.indices) {
            s[row] = filePath + listOfCsv[row].file_name + ".csv"
        }

        // Check recoded file in Database is actually available in SD card or not.
        var isFilesAvailableForZipping = false
        for (stringFilePath in s) {
            val f = File(stringFilePath)
            if (f.exists()) {
                isFilesAvailableForZipping = true
                break
            }
        }

        // Zip and delete available csvFiles
        if (isFilesAvailableForZipping) {
            val zipManager = ZipManager()
            zipManager.zip(s)
            for (stringFilePath in s) {
                val f = File(stringFilePath)
                f.delete()
            }
        }

        // Delete csvFiles record from DB
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        db!!.csvFileLogDao().deleteAllCSV()
    }

    fun archive(view: View?) {
        ZipAndDeleteTask().execute()
    }

    inner class DBTask : AsyncTask<Void, Void, Long>() {
        override fun doInBackground(vararg voids: Void): Long {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            return db!!.accLogDao().count
        }

        override fun onPostExecute(count: Long) {
            super.onPostExecute(count)
            tv_db_record_count_acc_table!!.text = "Accelerometer table row count: $count"
        }
    }

    inner class FetchTimeStampDBTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg voids: Void): String {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val startingTimeStamp = db!!.accLogDao().startingTimeStamp
            val lastRecordTime = db!!.accLogDao().lastRecordTime
            return "Starting timestamp: ${DateFormatter.getTimeStamp(startingTimeStamp)}\nLast timestamp: ${DateFormatter.getTimeStamp(lastRecordTime)}"
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            tv_db_record_count_event_table!!.text = s
        }
    }

    inner class CsvFileDBTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg voids: Void): String {
            val stringBuilder = StringBuilder()
            stringBuilder.append("| FileName                                  |  rows |\n")
            stringBuilder.append("|-----------------------------------------------|----------|\n")
            val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val listOfCsv = db.csvFileLogDao().all
            for (row in listOfCsv.indices) {
                stringBuilder.append(String.format("| %20s | %5s |\n", listOfCsv[row].file_name, listOfCsv[row].count))
            }
            println(stringBuilder.toString())
            return stringBuilder.toString()
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            tv_csv_list_table!!.text = s
        }
    }

    inner class ZipAndDeleteTask : AsyncTask<Void, Void, Unit>() {
        override fun doInBackground(vararg voids: Void): Unit {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val listOfCsv = db!!.csvFileLogDao().all
            if (listOfCsv.size > 0) {             // Avoid creating 0 byte Zip file, When no csvFile available.
                /** Zip CSV files and delete the original .csv files  */
                zipAllCSVFiles(listOfCsv)
            }
        }

        override fun onPostExecute(unit: Unit) {
            super.onPostExecute(unit)
            Toast.makeText(this@DatabaseActivity, "Zip successful", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val TAG = "DatabaseActivity"
    }
}