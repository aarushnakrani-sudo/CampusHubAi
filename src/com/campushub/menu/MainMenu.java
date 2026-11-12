package com.campushub.menu;

import com.campushub.service.LoginService;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final LoginService loginService = new LoginService();

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("             CAMPUS HUB AI");
            System.out.println("==========================================");
            System.out.println("1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.println("==========================================");

            System.out.print("Enter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:
                    loginService.studentLogin();
                    break;

                case 2:
                    loginService.adminLogin();
                    break;

                case 3:
                    System.out.println("\nThank You for using Campus Hub AI.");
                    System.exit(0);

                default:
                    System.out.println("\nInvalid Choice!");
            }

        }

    }

}