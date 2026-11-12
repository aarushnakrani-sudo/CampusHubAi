package com.campushub.main;

import com.campushub.service.AdminLostFoundService;

public class AdminLostFoundTest {

    public static void main(String[] args) {

        AdminLostFoundService service = new AdminLostFoundService();
        service.manageLostFound();

    }

}