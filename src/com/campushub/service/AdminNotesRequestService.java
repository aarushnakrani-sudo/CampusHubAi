package com.campushub.service;

import com.campushub.dao.AdminNotesRequestDAO;
import com.campushub.model.NotesRequest;
import com.campushub.utils.FileManager;

import java.util.List;
import java.util.Scanner;

public class AdminNotesRequestService {

    private AdminNotesRequestDAO requestDAO = new AdminNotesRequestDAO();

    public void manageRequests() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("         NOTE REQUEST MANAGEMENT");
            System.out.println("==================================================");

            List<NotesRequest> requests = requestDAO.getAllRequests();

            if (requests.isEmpty()) {

                System.out.println("No Note Requests Found.");

            } else {

                for (NotesRequest request : requests) {

                    System.out.println("Request ID : " + request.getRequestId());
                    System.out.println("Student    : " + request.getStudent().getFullName());
                    System.out.println("Subject    : " + request.getSubject().getSubjectName());
                    System.out.println("Topic      : " + request.getTopic());
                    System.out.println("Status     : " + request.getStatus());
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println("\n1. Approve Request");
            System.out.println("2. Reject Request");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Enter Request ID : ");
                    int approveId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (requestDAO.approveRequest(approveId)) {

                        FileManager.writeLog(
                                "Admin Approved Notes Request : " + approveId
                        );

                        System.out.println("\nRequest Approved Successfully.");

                    } else {

                        System.out.println("\nInvalid Request ID.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Request ID : ");
                    int rejectId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (requestDAO.rejectRequest(rejectId)) {

                        FileManager.writeLog(
                                "Admin Rejected Notes Request : " + rejectId
                        );

                        System.out.println("\nRequest Rejected Successfully.");

                    } else {

                        System.out.println("\nInvalid Request ID.");

                    }

                    break;

                case 3:

                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}