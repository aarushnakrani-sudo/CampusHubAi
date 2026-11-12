package com.campushub.service;

import com.campushub.dao.AdminLostFoundDAO;
import com.campushub.model.LostFound;

import java.util.List;
import java.util.Scanner;

public class AdminLostFoundService {

    private AdminLostFoundDAO lostFoundDAO = new AdminLostFoundDAO();

    public void manageLostFound() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("          LOST & FOUND MANAGEMENT");
            System.out.println("==================================================");

            List<LostFound> items = lostFoundDAO.getAllItems();

            if (items.isEmpty()) {

                System.out.println("No Records Found.");

            } else {

                for (LostFound item : items) {

                    System.out.println("Item ID    : " + item.getItemId());
                    System.out.println("Student    : " + item.getStudent().getFullName());
                    System.out.println("Item       : " + item.getItemName());
                    System.out.println("Type       : " + item.getType());
                    System.out.println("Location   : " + item.getLocation());
                    System.out.println("Status     : " + item.getStatus());
                    System.out.println("Image Path : " + (item.getImagePath() == null ? "None" : item.getImagePath()));
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println("\n1. Mark Item as Returned");
            System.out.println("2. View Uploaded Image");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Enter Item ID : ");
                    int itemId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    boolean itemExists = false;
                    for (LostFound item : items) {
                        if (item.getItemId() == itemId) {
                            itemExists = true;
                            break;
                        }
                    }

                    if (!itemExists) {
                        System.out.println("\nInvalid Item ID! Please select an active item from the list.");
                        break;
                    }

                    if (lostFoundDAO.markReturned(itemId)) {

                        System.out.println("\nItem Marked as Returned Successfully.");

                    } else {

                        System.out.println("\nItem Not Found.");

                    }

                    break;

                case 2:

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
                        System.out.println("\nInvalid Item ID! Please select an active item from the list.");
                        break;
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
                    break;

                case 3:
                    return;

                default:
                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}