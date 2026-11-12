package com.campushub.main;

import com.campushub.service.AnnouncementService;

public class AnnouncementTest {

    public static void main(String[] args) {

        AnnouncementService announcementService = new AnnouncementService();
        announcementService.showAnnouncements();

    }

}