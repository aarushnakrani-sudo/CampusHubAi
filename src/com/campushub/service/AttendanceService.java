package com.campushub.service;

import com.campushub.dao.AttendanceDAO;
import com.campushub.model.Attendance;
import com.campushub.model.Student;

import java.util.List;
import java.util.Scanner;

public class AttendanceService {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void showAttendance(Student student) {

        List<Attendance> attendanceList =
                attendanceDAO.getAttendanceByStudentId(student.getStudentId());

        if (attendanceList.isEmpty()) {

            System.out.println("\nNo attendance records found.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("             ATTENDANCE REPORT");
        System.out.println("==============================================");
        System.out.println("Student    : " + student.getFullName());
        System.out.println("Department : " + student.getDepartment().getDepartmentName());
        System.out.println("Semester   : " + student.getSemester());
        System.out.println("==============================================");

        System.out.printf("%-38s %-8s %-8s %-8s%n",
                "Subject", "Present", "Total", "%");

        System.out.println("--------------------------------------------------------------");

        double totalPresent = 0;
        double totalClasses = 0;

        for (Attendance attendance : attendanceList) {

            double percentage = attendance.getTotalClasses() > 0
                    ? (attendance.getPresentClasses() * 100.0) / attendance.getTotalClasses()
                    : 0.0;

            totalPresent += attendance.getPresentClasses();
            totalClasses += attendance.getTotalClasses();

            System.out.printf("%-38s %-8d %-8d %-7.2f%n",
                    attendance.getSubject().getSubjectName(),
                    attendance.getPresentClasses(),
                    attendance.getTotalClasses(),
                    percentage);

        }

        double overallPercentage = totalClasses > 0 ? (totalPresent * 100.0) / totalClasses : 0.0;

        System.out.println("--------------------------------------------------------------");

        System.out.printf("Overall Attendance : %.2f%%\n", overallPercentage);

        System.out.println("==============================================");

        System.out.println("\nPress ENTER to return...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }

}