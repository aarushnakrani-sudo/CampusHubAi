package com.campushub.main;

import com.campushub.database.DbMigration;
import com.campushub.menu.MainMenu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CampusHubAI {

    public static void main(String[] args) {

        // Redirect standard error to logs/error.txt to suppress raw SQL stack traces in console
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            PrintStream errStream = new PrintStream(new FileOutputStream("logs/error.txt", true));
            System.setErr(errStream);
        } catch (Exception ignored) {
        }

        DbMigration.runMigrations();

        MainMenu menu = new MainMenu();
        menu.start();

    }

}
