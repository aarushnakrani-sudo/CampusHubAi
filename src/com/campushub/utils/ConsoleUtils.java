package com.campushub.utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static int readInt(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static java.sql.Date readDate(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return java.sql.Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid date format (yyyy-mm-dd). Please enter a valid date: ");
            }
        }
    }

}
