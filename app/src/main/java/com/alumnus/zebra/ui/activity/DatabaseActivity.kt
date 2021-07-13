package com.alumnus.zebra.ui.activity

import android.content.Context
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
import kotlinx.coroutines.*
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

    fun getTotalCount(view: View) {
        //DBTask().execute()
        CoroutineScope(Dispatchers.Main).launch {
            val deferredLong: Deferred<Long> = CoroutineScope(Dispatchers.IO).async {
                getRowCountOfAccelerometerTable()
            }
            val rowCountLong = deferredLong.await()
            tv_db_record_count_acc_table!!.text = "Accelerometer table row count: $rowCountLong"
        }
    }

    fun getLastTimeStamp(v: View) {
        //FetchTimeStampDBTask().execute()
        CoroutineScope(Dispatchers.Main).launch {
            val deferredString: Deferred<String> = CoroutineScope(Dispatchers.IO).async {
                fetchTimeStampDBTask()
            }
            val timeStampString = deferredString.await()
            tv_db_record_count_event_table!!.text = timeStampString
        }
    }

    fun getCsvListTable(view: View) {
        //CsvFileDBTask().execute()
        CoroutineScope(Dispatchers.Main).launch {
            val deferredString: Deferred<String> = CoroutineScope(Dispatchers.IO).async {
                getRecordOfCsvFilesAvailableInSdCardFromDatabase()
            }
            val csvFileListString = deferredString.await()
            tv_csv_list_table!!.text = csvFileListString
        }
        Toast.makeText(this, "Chunk size: ${Constant.DATA_CHUNK_SIZE} \nRetain files count: ${Constant.RETAIN_NUMBER_OF_CSV_FILE}", Toast.LENGTH_SHORT).show()
    }

    /**
     * Suspended function. Must be running in background Thread always.
     * @param listOfCsv ArrayList of [CsvFileLogEntity]
     */
    private fun zipAllCSVFiles(listOfCsv: List<CsvFileLogEntity>) {
        val s = Array<String>(listOfCsv.size) { "it = $it" }    //TODO understand
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

    fun archive(view: View) {
        //ZipAndDeleteTask().execute()
        CoroutineScope(Dispatchers.IO).launch {
            deleteAfterZippingFiles()
        }
    }

    //TODO
    /*inner class DBTask : AsyncTask<Void, Void, Long>() {
        override fun doInBackground(vararg voids: Void): Long {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            return db!!.accLogDao().count
        }

        override fun onPostExecute(count: Long) {
            super.onPostExecute(count)
            tv_db_record_count_acc_table!!.text = "Accelerometer table row count: $count"
        }
    }*/

    private suspend fun getRowCountOfAccelerometerTable(): Long {
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        return db!!.accLogDao().count
    }

    /*inner class FetchTimeStampDBTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg voids: Void): String {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val startingTimeStamp = db!!.accLogDao().getStartingTimeStamp()
            val lastRecordTime = db!!.accLogDao().getLastRecordTime()
            return "Starting timestamp: ${DateFormatter.getTimeStamp(startingTimeStamp)}\nLast timestamp: ${DateFormatter.getTimeStamp(lastRecordTime)}"
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            tv_db_record_count_event_table!!.text = s
        }
    }*/

    private suspend fun fetchTimeStampDBTask(): String {
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        val startingTimeStamp = db!!.accLogDao().getStartingTimeStamp()
        val lastRecordTime = db!!.accLogDao().getLastRecordTime()
        return "Starting timestamp: ${DateFormatter.getTimeStamp(startingTimeStamp)}\nLast timestamp: ${DateFormatter.getTimeStamp(lastRecordTime)}"
    }

    /*inner class CsvFileDBTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg voids: Void): String {
            val stringBuilder = StringBuilder()
            stringBuilder.append("| FileName                                  |  rows |\n")
            stringBuilder.append("|-----------------------------------------------|----------|\n")
            val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val listOfCsv = db.csvFileLogDao().getAll()
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
    }*/

    private suspend fun getRecordOfCsvFilesAvailableInSdCardFromDatabase(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("| FileName                                  |  rows |\n")
        stringBuilder.append("|-----------------------------------------------|----------|\n")
        val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        val listOfCsv = db.csvFileLogDao().getAll()
        for (row in listOfCsv.indices) {
            stringBuilder.append(String.format("| %20s | %5s |\n", listOfCsv[row].file_name, listOfCsv[row].count))
        }
        println(stringBuilder.toString())
        return stringBuilder.toString()
    }

    /*inner class ZipAndDeleteTask : AsyncTask<Void, Void, Unit>() {
        override fun doInBackground(vararg voids: Void): Unit {
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val listOfCsv = db!!.csvFileLogDao().getAll()
            if (listOfCsv.size > 0) {             // Avoid creating 0 byte Zip file, When no csvFile available.

                zipAllCSVFiles(listOfCsv)
            }
        }

        override fun onPostExecute(unit: Unit) {
            super.onPostExecute(unit)
            Toast.makeText(this@DatabaseActivity, "Zip successful", Toast.LENGTH_LONG).show()
        }
    }*/

    private suspend fun deleteAfterZippingFiles() {
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        val listOfCsv = db!!.csvFileLogDao().getAll()
        if (listOfCsv.isNotEmpty()) {             // Avoid creating 0 byte Zip file, When no csvFile available.
            /** Zip CSV files and delete the original .csv files  */
            zipAllCSVFiles(listOfCsv)
        }
    }

    companion object {
        private const val TAG = "DatabaseActivity"
    }
}