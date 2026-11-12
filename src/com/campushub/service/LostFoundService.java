package com.campushub.service;

import com.campushub.dao.LostFoundDAO;
import com.campushub.model.LostFound;
import com.campushub.model.Student;

import java.util.List;
import java.util.Scanner;

public class LostFoundService {

    private LostFoundDAO dao = new LostFoundDAO();

    public void showLostFound(Student student) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n========== LOST & FOUND ==========");

            List<LostFound> items = dao.getAllItems();

            for (LostFound item : items) {

                System.out.println("Item ID  : " + item.getItemId());
                System.out.println("Item     : " + item.getItemName());
                System.out.println("Type     : " + item.getType());
                System.out.println("Location : " + item.getLocation());
                System.out.println("Status   : " + item.getStatus());
                System.out.println("Owner    : " + item.getStudent().getFullName());
                System.out.println("Image    : " + (item.getImagePath() == null ? "None" : item.getImagePath()));
                System.out.println("----------------------------------------");

            }

            System.out.println("\n1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. View Uploaded Image");
            System.out.println("4. Back");

            System.out.print("\nChoice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            if (choice == 4)
                return;

            if (choice == 3) {
                System.out.print("Enter Item ID to View Image: ");
                int viewId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                LostFound viewItem = null;
                for (LostFound item : items) {
                    if (item.getItemId() == viewId) {
                        viewItem = item;
                        break;
                    }
                }

                if (viewItem == null) {
                    System.out.println("\nInvalid Item ID! Please select an item from the list.");
                    continue;
                }

                String imagePath = viewItem.getImagePath();
                if (imagePath == null || imagePath.trim().isEmpty() || imagePath.equalsIgnoreCase("null")) {
                    System.out.println("\nNo image uploaded for this item.");
                } else {
                    java.io.File file = new java.io.File(imagePath);
                    if (file.exists()) {
                        System.out.println("\nOpening image: " + file.getAbsolutePath());
                        try {
                            if (java.awt.Desktop.isDesktopSupported() && java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
                                java.awt.Desktop.getDesktop().open(file);
                            } else {
                                Runtime.getRuntime().exec("cmd.exe /c start \"\" \"" + file.getAbsolutePath() + "\"");
                            }
                        } catch (Exception e) {
                            System.out.println("Unable to open image viewer. File path: " + file.getAbsolutePath());
                        }
                    } else {
                        System.out.println("\nImage file not found on system: " + file.getAbsolutePath());
                    }
                }
                continue;
            }

            if (choice != 1 && choice != 2) {
                System.out.println("\nInvalid Choice.");
                continue;
            }

            System.out.print("Item Name : ");
            String item = scanner.nextLine();

            System.out.print("Description : ");
            String description = scanner.nextLine();

            System.out.print("Location : ");
            String location = scanner.nextLine();

            System.out.println("Upload Image?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Choice : ");
            int imgChoice = com.campushub.utils.ConsoleUtils.readInt(scanner);
            String imagePath = null;
            if (imgChoice == 1) {
                String rawPath;
                while (true) {
                    System.out.print("Enter local image path (e.g. C:/temp/image.png) or 'cancel' to abort: ");
                    rawPath = scanner.nextLine().trim();

                    if (rawPath.equalsIgnoreCase("cancel")) {
                        System.out.println("Image upload cancelled.");
                        rawPath = "";
                        break;
                    }

                    if (rawPath.isEmpty()) {
                        System.out.println("Path cannot be empty!");
                        continue;
                    }

                    java.io.File file = new java.io.File(rawPath);
                    if (file.exists() && file.isFile()) {
                        break;
                    }
                    System.out.println("File not found. Please enter a valid existing image file path.");
                }

                if (!rawPath.isEmpty()) {
                    imagePath = com.campushub.utils.FileManager.copyFile(rawPath, "uploads/images");
                    if (imagePath == null) {
                        System.out.println("\nImage Copy Failed. Proceeding without image.");
                    } else {
                        System.out.println("\nImage uploaded successfully to: " + imagePath);
                    }
                }
            }

            String type = (choice == 1) ? "Lost" : "Found";

            if (dao.addItem(student.getStudentId(),
                    item,
                    description,
                    location,
                    type,
                    imagePath)) {

                System.out.println("\nReport Submitted Successfully.");

            } else {

                System.out.println("\nUnable to Submit Report.");

            }

        }

    }

}