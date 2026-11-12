package com.campushub.main;

import com.campushub.service.AdminAttendanceManagementService;

public class AdminAttendanceManagementTest {

    public static void main(String[] args) {

        AdminAttendanceManagementService service =
                new AdminAttendanceManagementService();

        service.manageAttendance();

    }

}