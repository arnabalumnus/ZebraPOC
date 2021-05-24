package com.alumnus.zebra.machineLearning.utils

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.alumnus.zebra.db.AppDatabase
import com.alumnus.zebra.db.entity.CsvFileLogEntity
import com.alumnus.zebra.machineLearning.DataAnalysis
import com.alumnus.zebra.pojo.AccelerationNumericData
import com.alumnus.zebra.utils.Constant
import com.alumnus.zebra.utils.CsvFileOperator
import com.alumnus.zebra.utils.DateFormatter
import com.alumnus.zebra.utils.FolderFiles
import com.alumnus.zebra.utils.FolderFiles.createFolder


/**
 * Handles all file write and delete operations
 * @author Arnab Kundu
 */
object ExportFiles {

    private const val TAG = "ExportFiles"

    /**
     * Exports data in Chunks from DB
     * Exports .csv & log file
     * Should be run in a background thread always
     * @param context Context Needed to access DB & External Storage
     */
    @Synchronized
    fun prepareDataChunk(context: Context) {
        val runnable = Runnable {
            val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-name").build()

            //try {
            while (db.accLogDao().count > Constant.DATA_CHUNK_SIZE) {
                val chunkFileName = generateChunkFileName(context)

                // TODO decide
                //  1st process (drawbacks got Database is locked)
                /*val accLogEntities = db.accLogDao().processDataChunk(Constant.DATA_CHUNK_SIZE)*/

                // TODO or the
                //  2nd process
                val accLogEntities = db.accLogDao().getDataChunk(Constant.DATA_CHUNK_SIZE)
                db.accLogDao().deleteChunk(Constant.DATA_CHUNK_SIZE) // comment this line to stop clearing DB after Exporting csv file

                if (accLogEntities.size == 0) {
                    Log.w(TAG, "No data available in database")
                    return@Runnable
                }

                val accelerationsDataList: ArrayList<AccelerationNumericData> = ArrayList()
                for (accLogEntity in accLogEntities) {
                    accelerationsDataList.add(AccelerationNumericData(accLogEntity.ts, accLogEntity.x, accLogEntity.y, accLogEntity.z))
                }
                exportCSVFile(context, accelerationsDataList, chunkFileName)
                deleteOldCSVFile(context);
                exportLogFile(context, accelerationsDataList, chunkFileName)

            }
            /*} catch (sqlEx: Exception) {
                // Data table may have some unexpected values
                Log.e(TAG, sqlEx.message, sqlEx)
            }*/
        }
        Thread(runnable).start()
    }


    /**
     * Exports data csv file
     * @param context Context Needed to access DB & External Storage
     * @param accelerationsDataList Data
     * @param fileName CSV fileName
     */
    private fun exportCSVFile(context: Context, accelerationsDataList: ArrayList<AccelerationNumericData>, fileName: String) {

        // Create Database object
        val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-name").build()

        // Store exported .csv filename into DB, To delete saved .csv file later (for storage cleanup)
        db.csvFileLogDao().insert(CsvFileLogEntity(fileName, Constant.DATA_CHUNK_SIZE.toLong()))

        // Create folder
        createFolder(context, "csvData")

        // Create CSV file and write arrayList data into file
        CsvFileOperator.writeCsvFile(context, accelerationsDataList, "csvData", fileName)
    }


    /**
     * Delete old .csv file to keep the device storage clean.
     * Unless it will fill with large amount of unwanted csv files
     * @param context
     */
    public fun deleteOldCSVFile(context: Context) {
        val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-name").build()
        if (db.csvFileLogDao().csvFileCount > Constant.RETAIN_NUMBER_OF_CSV_FILE) {
            val fileName = db.csvFileLogDao().oldestCSVFile
            val isDeleteSuccessful = FolderFiles.deleteFile(context = context, folderName = "csvData", fileName = fileName, fileExtension = ".csv")
            if (isDeleteSuccessful)
                db.csvFileLogDao().deleteCSVRecord(fileName)
            else {
                // In false condition also delete the DB record.
                // Because that file not available in the device.
                db.csvFileLogDao().deleteCSVRecord(fileName)
            }
        }
    }


    /**
     *  Data Analysis & Exports log file
     * @param context Context Needed to access DB & External Storage
     * @param accelerationsDataList Data
     * @param fileName CSV fileName
     */
    private fun exportLogFile(context: Context, accelerationsDataList: ArrayList<AccelerationNumericData>, fileName: String) {
        DataAnalysis().startEventAnalysis(accelerationsDataList, context, fileName)
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