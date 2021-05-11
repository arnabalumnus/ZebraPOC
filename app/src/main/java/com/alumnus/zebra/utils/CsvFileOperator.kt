package com.alumnus.zebra.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.util.Log
import com.alumnus.zebra.pojo.AccelerationNumericData
import com.alumnus.zebra.pojo.AccelerationStringData
import com.opencsv.CSVWriter
import java.io.*
import java.nio.charset.StandardCharsets

object CsvFileOperator {

    private const val TAG = "CsvFileOperator"


    /**
     * Read data of .csv file from InputStream
     *
     * @param inputStream
     * @return ArrayList<AccelerationStringData>
     */
    fun readCsvFile(inputStream: InputStream): ArrayList<AccelerationStringData> {

        /* Reads text from character-input stream, buffering characters for efficient reading */
        val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
        val accelerations = ArrayList<AccelerationStringData>()
        var line = ""

        try {
            // If buffer is not empty
            while (reader.readLine().also { line = it } != null) {

                // use comma as separator columns of CSV
                val tokens = line.split(",").toTypedArray()
                // Read the data

                /* Acceleration String data collection */
                val acceleration = AccelerationStringData(
                        tokens[0].replace("\"", ""),
                        tokens[1].replace("\"", ""),
                        tokens[2].replace("\"", ""),
                        tokens[3].replace("\"", ""))
                accelerations.add(acceleration)
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error on line: $line")
        }
        return accelerations
    }


    /**
     * Write data into a .csv file
     *
     * @param context
     * @param dataList
     * @param fileName
     */
    fun writeCsvFile(context: Context, dataList: ArrayList<AccelerationNumericData>, folderName: String, fileName: String) {

        val file = FolderFiles.createFile(context, folderName, fileName, "csv")
        try {
            val csvWrite = CSVWriter(FileWriter(file))
            csvWrite.writeNext(arrayOf("TS", "X", "Y", "Z"))            // Write header

            for (data in dataList) {                                    // Write data
                val arrStr = arrayOf(data.ts.toString(), data.x.toString(), data.y.toString(), data.z.toString())
                csvWrite.writeNext(arrStr)
            }
            csvWrite.close()

            // Without MediaScan file not visible to in PC after connecting via USB
            MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null) { path, uri ->

            }
        } catch (sqlEx: Exception) {
            Log.e(TAG, sqlEx.message, sqlEx)
        }

    }
}