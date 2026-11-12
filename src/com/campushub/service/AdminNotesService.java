package com.campushub.service;

import com.campushub.dao.AdminNotesDAO;
import com.campushub.datastructure.ApprovalStack;
import com.campushub.model.NotesSubmission;

import java.util.List;
import java.util.Scanner;

public class AdminNotesService {

    private AdminNotesDAO notesDAO = new AdminNotesDAO();

    // Stack for storing latest approval/rejection actions
    private ApprovalStack approvalStack = new ApprovalStack();

    public void manageNotes(com.campushub.model.Admin admin) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("                 NOTES APPROVAL");
            System.out.println("==================================================");

            List<NotesSubmission> submissions = notesDAO.getAllSubmissions();

            if (submissions.isEmpty()) {

                System.out.println("No Notes Available.");

            } else {

                for (NotesSubmission submission : submissions) {

                    System.out.println("Submission ID : " + submission.getSubmissionId());
                    System.out.println("Student       : " + submission.getStudent().getFullName());
                    System.out.println("Topic         : " + submission.getRequest().getTopic());
                    System.out.println("File Name     : " + submission.getFileName());
                    System.out.println("Status        : " + submission.getApprovalStatus());
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println();
            System.out.println("1. Approve Notes");
            System.out.println("2. Reject Notes");
            System.out.println("3. View Last Action (Stack)");
            System.out.println("4. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Enter Submission ID : ");
                    int approveId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (notesDAO.updateStatus(approveId, "Approved", admin.getAdminId())) {

                        NotesSubmission submission = new NotesSubmission();
                        submission.setSubmissionId(approveId);
                        submission.setApprovalStatus("Approved");

                        approvalStack.push(submission);

                        System.out.println("\nNotes Approved Successfully.");

                    } else {

                        System.out.println("\nSubmission Not Found.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Submission ID : ");
                    int rejectId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (notesDAO.updateStatus(rejectId, "Rejected", admin.getAdminId())) {

                        NotesSubmission submission = new NotesSubmission();
                        submission.setSubmissionId(rejectId);
                        submission.setApprovalStatus("Rejected");

                        approvalStack.push(submission);

                        System.out.println("\nNotes Rejected Successfully.");

                    } else {

                        System.out.println("\nSubmission Not Found.");

                    }

                    break;

                case 3:

                    if (approvalStack.isEmpty()) {

                        System.out.println("\nNo Action Performed Yet.");

                    } else {

                        NotesSubmission last = approvalStack.peek();

                        System.out.println("\n========================================");
                        System.out.println("          LAST STACK ACTION");
                        System.out.println("========================================");
                        System.out.println("Submission ID : " + last.getSubmissionId());
                        System.out.println("Status        : " + last.getApprovalStatus());
                        System.out.println("========================================");

                    }

                    break;

                case 4:
                    return;

                default:
                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}