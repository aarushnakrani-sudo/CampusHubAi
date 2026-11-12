package com.campushub.main;

import com.campushub.service.AdminComplaintService;

public class AdminComplaintTest {

    public static void main(String[] args) {

        AdminComplaintService service = new AdminComplaintService();
        service.manageComplaints();

    }

}