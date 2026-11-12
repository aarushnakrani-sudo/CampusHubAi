package com.campushub.service;

import com.campushub.dao.AdminAttendanceManagementDAO;
import com.campushub.model.Attendance;

import java.util.List;
import java.util.Scanner;

public class AdminAttendanceManagementService {

    private AdminAttendanceManagementDAO attendanceDAO =
            new AdminAttendanceManagementDAO();

    public void manageAttendance() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================================================");
            System.out.println("              ATTENDANCE MANAGEMENT");
            System.out.println("==============================================================");

            List<Attendance> attendanceList = attendanceDAO.getAllAttendance();

            for (Attendance attendance : attendanceList) {

                double percentage = attendance.getTotalClasses() > 0
                        ? (attendance.getPresentClasses() * 100.0) / attendance.getTotalClasses()
                        : 0.0;

                System.out.printf(
                        "Student ID : %-2d | Subject ID : %-2d | %-20s | %-35s | %d/%d (%.2f%%)%n",
                        attendance.getStudent().getStudentId(),
                        attendance.getSubject().getSubjectId(),
                        attendance.getStudent().getFullName(),
                        attendance.getSubject().getSubjectName(),
                        attendance.getPresentClasses(),
                        attendance.getTotalClasses(),
                        percentage
                );

            }

            System.out.println("\n1. Update Attendance");
            System.out.println("2. Attendance Shortage Report (below 75%)");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            if (choice == 3) {
                return;
            }

            if (choice == 2) {
                showShortageReport(scanner);
                continue;
            }

            if (choice != 1) {
                System.out.println("\nInvalid Choice.");
                continue;
            }

            System.out.print("\nEnter Student ID : ");
            int studentId = com.campushub.utils.ConsoleUtils.readInt(scanner);

            System.out.print("Enter Subject ID : ");
            int subjectId = com.campushub.utils.ConsoleUtils.readInt(scanner);

            System.out.print("Enter Total Classes : ");
            int totalClasses = com.campushub.utils.ConsoleUtils.readInt(scanner);

            System.out.print("Enter Present Classes : ");
            int presentClasses = com.campushub.utils.ConsoleUtils.readInt(scanner);

            if (presentClasses > totalClasses) {

                System.out.println("\nPresent Classes cannot be greater than Total Classes.");
                continue;

            }

            boolean updated = attendanceDAO.updateAttendance(
                    studentId,
                    subjectId,
                    totalClasses,
                    presentClasses
            );

            if (updated) {

                System.out.println("\nAttendance Updated Successfully.");

            } else {

                System.out.println("\nAttendance Update Failed.");

            }

        }

    }

    private void showShortageReport(Scanner scanner) {
        System.out.println("\n==============================================================");
        System.out.println("            ATTENDANCE SHORTAGE REPORT (BELOW 75%)");
        System.out.println("==============================================================");

        List<String[]> report = attendanceDAO.getAttendanceShortageReport();

        if (report.isEmpty()) {
            System.out.println("No students with attendance below 75%.");
        } else {
            System.out.printf("%-12s | %-15s | %-30s | %-10s%n", "Student ID", "Enrollment No", "Full Name", "Att. %");
            System.out.println("----------------------------------------------------------------------------");
            for (String[] row : report) {
                System.out.printf("%-12s | %-15s | %-30s | %-10s%n", row[0], row[1], row[2], row[3]);
            }
        }

        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

}