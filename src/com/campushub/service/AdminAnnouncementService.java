package com.campushub.service;

import com.campushub.dao.AdminAnnouncementDAO;
import com.campushub.model.Announcement;

import java.util.List;
import java.util.Scanner;

public class AdminAnnouncementService {

    private AdminAnnouncementDAO announcementDAO = new AdminAnnouncementDAO();

    public void manageAnnouncements() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n========================================");
            System.out.println("      ANNOUNCEMENT MANAGEMENT");
            System.out.println("========================================");

            List<Announcement> announcements =
                    announcementDAO.getAllAnnouncements();

            if (announcements.isEmpty()) {

                System.out.println("No Announcements Found.");

            } else {

                for (Announcement announcement : announcements) {

                    System.out.println("ID          : " + announcement.getAnnouncementId());
                    System.out.println("Title       : " + announcement.getTitle());
                    System.out.println("Description : " + announcement.getDescription());
                    System.out.println("Published   : " + announcement.getCreatedAt().toLocalDate());
                    System.out.println("----------------------------------------");

                }

            }

            System.out.println("\n1. Add Announcement");
            System.out.println("2. Delete Announcement");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Title : ");
                    String title = scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    if (announcementDAO.addAnnouncement(1, title, description)) {

                        System.out.println("\nAnnouncement Added Successfully.");

                    } else {

                        System.out.println("\nUnable to Add Announcement.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Announcement ID : ");
                    int announcementId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (announcementDAO.deleteAnnouncement(announcementId)) {

                        System.out.println("\nAnnouncement Deleted Successfully.");

                    } else {

                        System.out.println("\nAnnouncement Not Found.");

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