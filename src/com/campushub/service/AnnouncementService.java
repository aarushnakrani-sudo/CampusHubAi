package com.campushub.service;

import com.campushub.dao.AnnouncementDAO;
import com.campushub.model.Announcement;

import java.util.List;
import java.util.Scanner;

public class AnnouncementService {

    private AnnouncementDAO announcementDAO = new AnnouncementDAO();

    public void showAnnouncements() {

        List<Announcement> announcementList =
                announcementDAO.getAllAnnouncements();

        if (announcementList.isEmpty()) {

            System.out.println("\nNo announcements available.");
            return;
        }

        System.out.println("\n==============================================================");
        System.out.println("                     ANNOUNCEMENTS");
        System.out.println("==============================================================");

        for (Announcement announcement : announcementList) {

            System.out.println("\nTitle : " + announcement.getTitle());
            System.out.println("Posted By : " + announcement.getAdmin().getName());
            System.out.println("Date : " + announcement.getCreatedAt());
            System.out.println("----------------------------------------------");
            System.out.println(announcement.getDescription());
            System.out.println("==============================================================");
        }

        System.out.println("\nPress ENTER to return...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }

}