package com.campushub.main;

import com.campushub.service.AdminEventService;

public class AdminEventTest {

    public static void main(String[] args) {

        AdminEventService service = new AdminEventService();
        service.manageEvents();

    }

}