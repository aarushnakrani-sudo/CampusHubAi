package com.campushub.service;

import com.campushub.dao.AdminComplaintDAO;
import com.campushub.model.Complaint;

import java.util.List;
import java.util.Scanner;
import com.campushub.datastructure.ComplaintQueue;
import java.util.Queue;
public class AdminComplaintService {

    private AdminComplaintDAO complaintDAO = new AdminComplaintDAO();
    private ComplaintQueue complaintQueue = new ComplaintQueue();

    public void manageComplaints() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("            COMPLAINT MANAGEMENT");
            System.out.println("==================================================");

            List<Complaint> complaints = complaintDAO.getAllComplaints();

// Clear old queue
            complaintQueue.clear();

// Load latest complaints
            for (Complaint complaint : complaints) {

                complaintQueue.enqueue(complaint);

            }
            // Load complaints into Queue
            Queue<Complaint> queue = complaintQueue.getQueue();

            for (Complaint complaint : queue) {

                System.out.println("Complaint ID : " + complaint.getComplaintId());
                System.out.println("Student      : " + complaint.getStudent().getFullName());
                System.out.println("Title        : " + complaint.getTitle());
                System.out.println("Description  : " + complaint.getDescription());
                System.out.println("Status       : " + complaint.getStatus());
                System.out.println("--------------------------------------------");

            }

            if (complaints.isEmpty()) {

                System.out.println("No Complaints Found.");

            } else {

                for (Complaint complaint : complaints) {

                    System.out.println("Complaint ID : " + complaint.getComplaintId());
                    System.out.println("Student      : " + complaint.getStudent().getFullName());
                    System.out.println("Title        : " + complaint.getTitle());
                    System.out.println("Description  : " + complaint.getDescription());
                    System.out.println("Status       : " + complaint.getStatus());
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println("\n1. Resolve Complaint");
            System.out.println("2. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Enter Complaint ID : ");
                    int complaintId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (complaintDAO.resolveComplaint(complaintId)) {



                        System.out.println("\nComplaint Resolved Successfully.");


                    }
                    else {

                        System.out.println("\nComplaint Not Found.");

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