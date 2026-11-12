package com.campushub.main;

import com.campushub.service.AdminAnnouncementService;

public class AdminAnnouncementTest {

    public static void main(String[] args) {

        AdminAnnouncementService service =
                new AdminAnnouncementService();

        service.manageAnnouncements();

    }

}