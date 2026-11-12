package com.campushub.dashboard;

import com.campushub.service.AdminAnnouncementService;
import com.campushub.service.AdminAttendanceManagementService;
import com.campushub.service.AdminClubService;
import com.campushub.service.AdminComplaintService;
import com.campushub.service.AdminEventService;
import com.campushub.service.AdminLostFoundService;
import com.campushub.service.AdminNotesRequestService;
import com.campushub.service.AdminNotesService;

import java.util.Scanner;

public class AdminDashboard {

    private Scanner scanner = new Scanner(System.in);
    private com.campushub.model.Admin loggedInAdmin;

    public void showDashboard(com.campushub.model.Admin admin) {
        this.loggedInAdmin = admin;

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("           ADMIN DASHBOARD");
            System.out.println("==========================================");

            System.out.println("1. Manage Attendance");
            System.out.println("2. Post Announcement");
            System.out.println("3. Notes Management");
            System.out.println("4. Manage Complaints");
            System.out.println("5. Manage Lost & Found");
            System.out.println("6. Manage Events");
            System.out.println("7. Manage Clubs");
            System.out.println("8. Logout");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    new AdminAttendanceManagementService()
                            .manageAttendance();
                    break;

                case 2:

                    new AdminAnnouncementService()
                            .manageAnnouncements();
                    break;

                case 3:

                    showNotesManagementMenu();
                    break;

                case 4:

                    new AdminComplaintService()
                            .manageComplaints();
                    break;

                case 5:

                    new AdminLostFoundService()
                            .manageLostFound();
                    break;

                case 6:

                    new AdminEventService()
                            .manageEvents();
                    break;

                case 7:

                    new AdminClubService()
                            .manageClubs();
                    break;

                case 8:

                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

    // =============================================
    // Notes Management Menu
    // =============================================

    private void showNotesManagementMenu() {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("          NOTES MANAGEMENT");
            System.out.println("==========================================");

            System.out.println("1. Manage Note Requests");
            System.out.println("2. Manage Uploaded Notes");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    new AdminNotesRequestService()
                            .manageRequests();

                    break;

                case 2:

                    new AdminNotesService()
                            .manageNotes(loggedInAdmin);

                    break;

                case 3:

                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}