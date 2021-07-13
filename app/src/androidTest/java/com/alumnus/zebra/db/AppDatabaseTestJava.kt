package com.alumnus.zebra.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alumnus.zebra.db.dao.AccLogDao
import com.alumnus.zebra.db.dao.CsvFileLogDao
import com.alumnus.zebra.db.entity.AccLogEntity
import com.alumnus.zebra.db.entity.CsvFileLogEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTestJava {
    private var db: AppDatabase? = null
    private var accLogDao: AccLogDao? = null
    private var csvFileLogDao: CsvFileLogDao? = null
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        accLogDao = db!!.accLogDao()
        csvFileLogDao = db!!.csvFileLogDao()
    }

    @Test
    fun testAccDao() {
        assert(accLogDao!!.count == 0L)
        val accLogEntity = AccLogEntity()
        accLogEntity.ts = System.currentTimeMillis()
        accLogEntity.x = 9.81f
        accLogEntity.y = 0.0f
        accLogEntity.z = 0.0f
        accLogDao!!.insert(accLogEntity) // 1st insert
        accLogDao!!.insert(accLogEntity) // Replace 1st insert (as Ts is unique)
        accLogEntity.ts = System.currentTimeMillis()
        accLogDao!!.insert(accLogEntity) // 2nd insert
        accLogEntity.ts = System.currentTimeMillis()
        accLogDao!!.insert(accLogEntity) // 3rd insert
        assert(accLogDao!!.count == 3L) // Test Size. Should be 3

        accLogDao!!.deleteAll(System.currentTimeMillis()) // Delete all.
        assert(accLogDao!!.count == 0L) // Test Size. Should be 0

    }

    @Test
    fun testCsvDao() {
        val csvFileLogEntity = CsvFileLogEntity("Dummy file name", 100L)
        csvFileLogDao!!.insert(csvFileLogEntity) // 1st insert
        csvFileLogDao!!.insert(csvFileLogEntity) // Replace 1st insert
        assert(csvFileLogDao!!.all.size == 1) // Test size should be 1

        csvFileLogEntity.file_name = "Dummy file2"
        csvFileLogEntity.count = 200L
        csvFileLogDao!!.insert(csvFileLogEntity) // 2nd insert
        assert(csvFileLogDao!!.csvFileCount == 2) // Test size should be 2

        assert(csvFileLogDao!!.totalRecordOfAllCSVFile == 300L) // Test total record inserted. Should be 100 + 100 = 200

        assert(csvFileLogDao!!.oldestCSVFile.equals("Dummy file name", ignoreCase = true)) // Test oldestCSVFile() method

        csvFileLogDao!!.deleteCSVRecord("Dummy file name") // Delete csv file with given filename
        assert(!csvFileLogDao!!.oldestCSVFile.equals("Dummy file name", ignoreCase = true)) // Verify specified file deletion

        assert(csvFileLogDao!!.all.size == 1) // Test size should be 1

        csvFileLogDao!!.deleteAllCSV()          // Delete all
        assert(csvFileLogDao!!.all.isEmpty()) // Test size should be 0

    }

    @After
    fun tearDown() {
        db!!.close()
    }
}