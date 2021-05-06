package com.alumnus.zebra.machineLearning.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.util.Log
import androidx.room.Room
import com.alumnus.zebra.db.AppDatabase
import com.alumnus.zebra.machineLearning.MachineLearning
import com.alumnus.zebra.pojo.AccelerationData
import com.alumnus.zebra.utils.Constant
import com.alumnus.zebra.utils.DateFormatter
import com.alumnus.zebra.utils.FolderFiles.createFile
import com.alumnus.zebra.utils.FolderFiles.createFolder
import com.opencsv.CSVWriter
import java.io.FileWriter

object ExportFiles {

    private const val TAG = "ExportFiles"

    /**
     * Exports data in Chunks from DB
     * Exports .csv & log file
     * Should be run in a background thread always
     * @param context Context Needed to access DB & External Storage
     */
    fun prepareDataChunk(context: Context) {
        val runnable = Runnable {
            val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-name").build()
            while (db.accLogDao().count > Constant.DATA_CHUNK_SIZE) {
                val chunkFileName = generateChunkFileName(context)

                val accLogEntities = db.accLogDao().processDataChunk(Constant.DATA_CHUNK_SIZE)
                if (accLogEntities.size == 0) {
                    Log.w(TAG, "No data available in database")
                    return@Runnable
                }
                try {
                    val accelerationsDataList: ArrayList<AccelerationData> = ArrayList()
                    for (accLogEntity in accLogEntities) {
                        accelerationsDataList.add(AccelerationData(accLogEntity.ts, accLogEntity.x, accLogEntity.y, accLogEntity.z))
                    }
                    exportCSVFile(context, accelerationsDataList, chunkFileName)
                    exportLogFile(context, accelerationsDataList, chunkFileName)

                } catch (sqlEx: Exception) {
                    // Data table may have some unexpected values
                    Log.e(TAG, sqlEx.message, sqlEx)
                }
            }
        }
        Thread(runnable).start()
    }

    /**
     * Exports data csv file
     * @param context Context Needed to access DB & External Storage
     * @param accelerationsDataList Data
     * @param fileName CSV fileName
     */
    private fun exportCSVFile(context: Context, accelerationsDataList: ArrayList<AccelerationData>, fileName: String) {

        createFolder(context, "csvData")

        val file = createFile(context, "csvData", fileName, "csv")

        try {
            val csvWrite = CSVWriter(FileWriter(file))
            csvWrite.writeNext(arrayOf("TS", "X", "Y", "Z"))
            for (data in accelerationsDataList) {
                val arrStr = arrayOf(data.ts.toString(), data.x.toString(), data.y.toString(), data.z.toString())
                csvWrite.writeNext(arrStr)
            }
            csvWrite.close()
            MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null) { path, uri ->

            }
        } catch (sqlEx: Exception) {
            Log.e(TAG, sqlEx.message, sqlEx)
        }
    }

    /**
     *  Data Analysis & Exports log file
     * @param context Context Needed to access DB & External Storage
     * @param accelerationsDataList Data
     * @param fileName CSV fileName
     */
    private fun exportLogFile(context: Context, accelerationsDataList: ArrayList<AccelerationData>, fileName: String) {
        MachineLearning().CalculateTSV(accelerationsDataList, context, fileName)
    }

    /**
     * Generate fileName according to starting data row TimeStamp
     * @param context Required for DB access
     * @return fileName
     */
    private fun generateChunkFileName(context: Context): String {
        val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-name").build()
        return DateFormatter.getTimeStampFileName(db.accLogDao().startingTimeStamp)
    }
}