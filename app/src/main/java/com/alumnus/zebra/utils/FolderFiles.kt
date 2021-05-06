package com.alumnus.zebra.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import java.io.File
import java.io.IOException

/**
 * @author Arnab Kundu
 * @param context Context required for API level R
 */

object FolderFiles {

    const val PARENT_DIRECTORY_NAME = "ZebraApp"

    /**
     * Generic method to Create folder
     * @param context Context required for API level R
     * @param folderName
     */
    fun createFolder(context: Context? = null, folderName: String) {

        val logFolder: File = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            File(Environment.getExternalStorageDirectory(), "$PARENT_DIRECTORY_NAME/$folderName")
        else
            File(context?.getExternalFilesDir(PARENT_DIRECTORY_NAME), folderName)

        if (!logFolder.exists()) {
            try {
                logFolder.mkdirs()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    /**
     * Generic method to Create File
     * @param context Context required for API level R
     * @param folderName
     * @param fileName
     * @param fileExtension
     */
    fun createFile(context: Context? = null, folderName: String, fileName: String, fileExtension: String): File {

        val file: File = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            File(Environment.getExternalStorageDirectory(), "$PARENT_DIRECTORY_NAME/$folderName/$fileName.$fileExtension")
        else
            File(context?.getExternalFilesDir(PARENT_DIRECTORY_NAME), "$folderName/$fileName.$fileExtension")

        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file
    }

    /**
     * Generic method to Create Text File
     * @param context Context required for API level R
     * @param folderName
     * @param fileName
     */
    fun createTextFile(context: Context? = null, folderName: String, fileName: String): File {

        val textFile: File = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            File(Environment.getExternalStorageDirectory(), "$PARENT_DIRECTORY_NAME/$folderName/$fileName.txt")
        else
            File(context?.getExternalFilesDir(PARENT_DIRECTORY_NAME), "$folderName/$fileName.txt")

        if (!textFile.exists()) {
            try {
                textFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return textFile
    }


    /**
     * createLogFile() this method specfic to this project to save logs
     * @param context Context required for API level R
     * @param folderName
     * @param fileName
     */
    fun createLogFile(context: Context? = null, folderName: String, fileName: String): File {

        val logFile: File = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            File(Environment.getExternalStorageDirectory(), "$PARENT_DIRECTORY_NAME/$folderName/log-$fileName.txt")
        else
            File(context?.getExternalFilesDir(PARENT_DIRECTORY_NAME), "$folderName/log-$fileName.txt")

        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return logFile
    }
}