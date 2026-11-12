package com.campushub.service;

import com.campushub.dao.ComplaintDAO;
import com.campushub.model.Complaint;
import com.campushub.model.Student;

import java.util.List;
import java.util.Scanner;

public class ComplaintService {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    public void showComplaints(Student student) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n========================================");
            System.out.println("          MY COMPLAINTS");
            System.out.println("========================================");

            List<Complaint> complaints =
                    complaintDAO.getStudentComplaints(student.getStudentId());

            if (complaints.isEmpty()) {

                System.out.println("No complaints submitted.");

            } else {

                for (Complaint complaint : complaints) {

                    System.out.println("Complaint ID : " + complaint.getComplaintId());
                    System.out.println("Title        : " + complaint.getTitle());
                    System.out.println("Description  : " + complaint.getDescription());
                    System.out.println("Status       : " + complaint.getStatus());
                    System.out.println("----------------------------------------");

                }

            }

            System.out.println("\n1. Submit Complaint");
            System.out.println("2. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Title : ");
                    String title = scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    if (complaintDAO.addComplaint(
                            student.getStudentId(),
                            title,
                            description)) {

                        System.out.println("\nComplaint Submitted Successfully.");

                    } else {

                        System.out.println("\nUnable to Submit Complaint.");

                    }

                    break;

                case 2:
                    return;

                default:
                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}