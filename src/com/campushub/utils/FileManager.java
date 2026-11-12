package com.campushub.utils;

import java.io.*;

public class FileManager {

    // =============================
    // Copy File using Streams
    // =============================

    public static String copyFile(String sourcePath, String destinationFolder) {

        try {

            File sourceFile = new File(sourcePath);

            if (!sourceFile.exists()) {

                return null;

            }

            File folder = new File(destinationFolder);

            if (!folder.exists()) {

                folder.mkdirs();

            }

            File destinationFile =
                    new File(folder, sourceFile.getName());

            FileInputStream fis =
                    new FileInputStream(sourceFile);

            FileOutputStream fos =
                    new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = fis.read(buffer)) != -1) {

                fos.write(buffer, 0, length);

            }

            fis.close();
            fos.close();

            return destinationFile.getAbsolutePath();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }

    // =============================
    // Write Activity Log
    // =============================

    public static void writeLog(String message) {

        try {

            FileWriter fw =
                    new FileWriter("logs/activity.txt", true);

            BufferedWriter bw =
                    new BufferedWriter(fw);

            bw.write(message);

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // =============================
    // Read Activity Log
    // =============================

    public static void readLog() {

        try {

            FileReader fr =
                    new FileReader("logs/activity.txt");

            BufferedReader br =
                    new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                System.out.println(line);

            }

            br.close();

        } catch (IOException e) {

            System.out.println("No Activity Log Found.");

        }

    }

}