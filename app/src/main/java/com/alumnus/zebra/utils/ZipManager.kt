package com.alumnus.zebra.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Zip & UnZip files
 *
 * @author Arnab Kundu
 */
public class ZipManager {

    int BUFFER = 100000; // TODO variable according to buffer size

    /**
     * Zip files
     * ** How to use **
     * <p>
     * String[] s = new String[2]; // Array of files
     * s[0] = inputPath + "/image.jpg";
     * s[1] = inputPath + "/textfile.txt"; // /sdcard/ZipDemo/textfile.txt
     * </br>
     * ZipManager zipManager = new ZipManager();
     * zipManager.zip(s, inputPath + inputFile); // first parameter is d files second parameter is zip file name & path
     * </p>
     *
     * @param _files      Array of files to be zipped
     * @param zipFileName Provide a name for the zip file which will be generated
     */
    public void zip(final String[] _files, String zipFileName) {

        String pathOfZebraFolder = FolderFiles.INSTANCE.createFolder(null, "zipFiles/");
        if (zipFileName == null || zipFileName.length() == 0) {
            zipFileName = DateFormatter.getTimeStampFileName(System.currentTimeMillis());
        }
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(pathOfZebraFolder + "/" + zipFileName + ".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < _files.length; i++) {
                Log.v("Compress", "Adding: " + _files[i]);
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, BUFFER);

                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;

                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ZipManager zipManager = new ZipManager();
     * zipManager.unzip(inputPath + inputFile, outputPath);
     *
     * @param _zipFile        Fully qualified filename. i.e path + filename
     * @param _targetLocation Folder location where files will be saved after extraction
     *                        TODO Need to implement based on requirement
     */
    public void unzip(String _zipFile, String _targetLocation) {

        //create target location folder if not exist
        //TODO dirChecker(_targetLocatioan);

        try {
            FileInputStream fin = new FileInputStream(_zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {

                //create dir if required while unzipping
                if (ze.isDirectory()) {
                    //TODO dirChecker(ze.getName());
                } else {
                    FileOutputStream fout = new FileOutputStream(_targetLocation + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }

                    zin.closeEntry();
                    fout.close();
                }

            }
            zin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
