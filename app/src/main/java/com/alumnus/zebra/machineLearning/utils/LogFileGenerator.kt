package com.alumnus.zebra.machineLearning.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

object LogFileGenerator {


    fun appendLog(context: Context, mFileName: String, text: String) {
        // var logFile: File
        val logFile: File = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            File(Environment.getExternalStorageDirectory(), "ZebraApp/log-$mFileName.txt")
        else
            File(context.getExternalFilesDir("ZebraApp"), "log-$mFileName.txt")

        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

}