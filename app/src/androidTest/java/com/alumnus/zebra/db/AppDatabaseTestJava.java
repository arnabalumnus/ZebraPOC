package com.alumnus.zebra.db;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alumnus.zebra.db.dao.AccLogDao;
import com.alumnus.zebra.db.dao.CsvFileLogDao;
import com.alumnus.zebra.db.entity.AccLogEntity;
import com.alumnus.zebra.db.entity.CsvFileLogEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTestJava {

    private AppDatabase db;
    private AccLogDao accLogDao;
    private CsvFileLogDao csvFileLogDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        accLogDao = db.accLogDao();
        csvFileLogDao = db.csvFileLogDao();
    }

    @Test
    public void testAccDao() {
        assert (accLogDao.getCount() == 0L);
        AccLogEntity accLogEntity = new AccLogEntity();
        accLogEntity.setTs(System.currentTimeMillis());
        accLogEntity.setX(9.81F);
        accLogEntity.setY(0.0F);
        accLogEntity.setZ(0.0F);
        accLogDao.insert(accLogEntity);
        accLogDao.insert(accLogEntity);
        accLogEntity.setTs(System.currentTimeMillis());
        accLogDao.insert(accLogEntity);
        accLogEntity.setTs(System.currentTimeMillis());
        accLogDao.insert(accLogEntity);
        assert (accLogDao.getCount() == 3L);
        System.out.println(accLogDao.getAll().get(0).toString());
        //assert (accLogDao.getAll().contains(accLogEntity));
        accLogDao.deleteAll(System.currentTimeMillis());
        assert (accLogDao.getCount() == 0L);
    }

    @Test
    public void testCsvDao() {
        CsvFileLogEntity csvFileLogEntity = new CsvFileLogEntity("Dummy file name", 100L);
        csvFileLogDao.insert(csvFileLogEntity);                         // 1st insert
        csvFileLogDao.insert(csvFileLogEntity);                         // Replace 1st insert
        assert (csvFileLogDao.getAll().size() == 1);                    // Test size should be 1

        csvFileLogEntity.file_name = "Dummy file2";
        csvFileLogEntity.count = 200L;
        csvFileLogDao.insert(csvFileLogEntity);                         // 2nd insert
        assert (csvFileLogDao.getCSVFileCount() == 2);                  // Test size should be 2

        assert (csvFileLogDao.getTotalRecordOfAllCSVFile() == 300L);    // Test total record inserted. Should be 100 + 100 = 200

        assert (csvFileLogDao.getOldestCSVFile().equalsIgnoreCase("Dummy file name"));          // Test oldestCSVFile() method
        csvFileLogDao.deleteCSVRecord("Dummy file name");                                         // Delete csv file with given filename
        assert (!csvFileLogDao.getOldestCSVFile().equalsIgnoreCase("Dummy file name"));         // Verify specified file deletion

        assert (csvFileLogDao.getAll().size() == 1);                            // Test size should be 1
        csvFileLogDao.deleteAllCSV();                                           // Delete all
        assert (csvFileLogDao.getAll().size() == 0);                            // Test size should be 0
    }

    @After
    public void tearDown() {
        db.close();
    }
}