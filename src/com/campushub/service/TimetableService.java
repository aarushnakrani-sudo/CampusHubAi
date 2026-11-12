package com.campushub.service;

import com.campushub.dao.TimetableDAO;
import com.campushub.model.Student;
import com.campushub.model.Timetable;

import java.util.List;
import java.util.Scanner;

public class TimetableService {

    private TimetableDAO timetableDAO = new TimetableDAO();

    public void showTimetable(Student student) {

        List<Timetable> timetableList =
                timetableDAO.getTimetableBySemester(student.getSemester(), student.getDepartment().getDepartmentId());

        if (timetableList.isEmpty()) {

            System.out.println("\nNo timetable found.");
            return;
        }

        System.out.println("\n==============================================================");
        System.out.println("                     CLASS TIMETABLE");
        System.out.println("==============================================================");
        System.out.println("Student    : " + student.getFullName());
        System.out.println("Department : " + student.getDepartment().getDepartmentName());
        System.out.println("Semester   : " + student.getSemester());
        System.out.println("==============================================================");

        System.out.printf("%-10s %-18s %-35s %-20s %-8s%n",
                "Day", "Time", "Subject", "Faculty", "Room");

        System.out.println("---------------------------------------------------------------------------------------------");

        for (Timetable timetable : timetableList) {

            System.out.printf("%-10s %-18s %-35s %-20s %-8s%n",
                    timetable.getDay(),
                    timetable.getTimeSlot(),
                    timetable.getSubject().getSubjectName(),
                    timetable.getFaculty().getFacultyName(),
                    timetable.getRoomNo());

        }

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("\nPress ENTER to return...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }

}