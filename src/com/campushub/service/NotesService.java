package com.campushub.service;

import com.campushub.dao.NotesRequestDAO;
import com.campushub.dao.NotesSubmissionDAO;
import com.campushub.dao.StudentNotesSubmissionDAO;
import com.campushub.model.NotesRequest;
import com.campushub.model.NotesSubmission;
import com.campushub.model.Student;
import com.campushub.model.Subject;

import java.util.List;
import java.util.Scanner;

public class NotesService {

    private NotesRequestDAO requestDAO = new NotesRequestDAO();
    private NotesSubmissionDAO submissionDAO = new NotesSubmissionDAO();
    private StudentNotesSubmissionDAO studentSubmissionDAO = new StudentNotesSubmissionDAO();

    public void showNotes(Student student) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("                NOTES SHARING SYSTEM");
            System.out.println("==================================================");
            System.out.println("1. Request Notes");
            System.out.println("2. Upload Notes (PDF)");
            System.out.println("3. View Notes Requests");
            System.out.println("4. View Available Notes");
            System.out.println("5. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    requestNotes(student, scanner);
                    break;

                case 2:

                    uploadNotesFlow(student, scanner);
                    break;

                case 3:

                    viewRequests(scanner);
                    break;

                case 4:

                    viewAvailableNotes(scanner);
                    break;

                case 5:

                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

    private void requestNotes(Student student, Scanner scanner) {
        System.out.println("\n--- REQUEST NEW NOTES ---");
        List<Subject> subjects = requestDAO.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects found in the system.");
            return;
        }

        System.out.println("Available Subjects:");
        for (Subject sub : subjects) {
            System.out.println("ID: " + sub.getSubjectId() + " | " + sub.getSubjectName());
        }

        System.out.print("\nEnter Subject ID : ");
        int subjectId = com.campushub.utils.ConsoleUtils.readInt(scanner);

        // Verify subject exists
        boolean subjectExists = false;
        for (Subject sub : subjects) {
            if (sub.getSubjectId() == subjectId) {
                subjectExists = true;
                break;
            }
        }

        if (!subjectExists) {
            System.out.println("\nInvalid Subject ID!");
            return;
        }

        System.out.print("Enter Topic : ");
        String topic = scanner.nextLine().trim();

        System.out.print("Enter Description : ");
        String description = scanner.nextLine().trim();

        if (topic.isEmpty()) {
            System.out.println("\nTopic cannot be empty!");
            return;
        }

        if (requestDAO.createRequest(student.getStudentId(), subjectId, topic, description)) {
            System.out.println("\nNotes request created successfully. Status: Pending Admin Approval.");
        } else {
            System.out.println("\nFailed to create notes request.");
        }
    }

    private void uploadNotesFlow(Student student, Scanner scanner) {
        System.out.println("\n--- UPLOAD NOTES PDF ---");
        List<NotesRequest> requests = requestDAO.getAllRequests();
        
        boolean hasApproved = false;
        for (NotesRequest req : requests) {
            if (req.getStudent().getStudentId() == student.getStudentId() && req.getStatus().equalsIgnoreCase("Approved")) {
                if (!hasApproved) {
                    System.out.println("Your Approved Note Requests:");
                    hasApproved = true;
                }
                System.out.println("Request ID : " + req.getRequestId() + " | Subject: " + req.getSubject().getSubjectName() + " | Topic: " + req.getTopic());
            }
        }

        if (!hasApproved) {
            System.out.println("You do not have any approved note requests to upload PDFs for.");
            return;
        }

        System.out.print("\nEnter Request ID : ");
        int requestId = com.campushub.utils.ConsoleUtils.readInt(scanner);

        // Verify that this request belongs to student and is Approved
        boolean isApprovedAndBelongs = false;
        for (NotesRequest req : requests) {
            if (req.getRequestId() == requestId && req.getStudent().getStudentId() == student.getStudentId() && req.getStatus().equalsIgnoreCase("Approved")) {
                isApprovedAndBelongs = true;
                break;
            }
        }

        if (!isApprovedAndBelongs) {
            System.out.println("\nInvalid Request ID! The request must belong to you and be Approved by an Admin.");
            return;
        }

        String pdfPath;
        while (true) {
            System.out.print("Enter local PDF file path (e.g. C:/my_notes.pdf) or 'cancel' to abort: ");
            pdfPath = scanner.nextLine().trim();

            if (pdfPath.equalsIgnoreCase("cancel")) {
                System.out.println("\nUpload aborted.");
                return;
            }

            if (pdfPath.isEmpty()) {
                System.out.println("File path cannot be empty!");
                continue;
            }

            java.io.File file = new java.io.File(pdfPath);
            if (file.exists() && file.isFile() && pdfPath.toLowerCase().endsWith(".pdf")) {
                break;
            }
            System.out.println("File not found or not a valid PDF. Please try again.");
        }

        if (studentSubmissionDAO.uploadNotes(requestId, student.getStudentId(), pdfPath)) {
            System.out.println("\nPDF uploaded successfully! Status: Pending Admin Verification.");
        } else {
            System.out.println("\nFailed to upload notes. Please check if the file path is correct.");
        }
    }

    private void viewRequests(Scanner scanner) {
        System.out.println("\n==================================================");
        System.out.println("                NOTES REQUESTS");
        System.out.println("==================================================");

        List<NotesRequest> requests = requestDAO.getAllRequests();

        if (requests.isEmpty()) {
            System.out.println("No Notes Requests Available.");
        } else {
            for (NotesRequest request : requests) {
                System.out.println("Request ID : " + request.getRequestId());
                System.out.println("Student    : " + request.getStudent().getFullName());
                System.out.println("Subject    : " + request.getSubject().getSubjectName());
                System.out.println("Topic      : " + request.getTopic());
                System.out.println("Status     : " + request.getStatus());
                System.out.println("----------------------------------------------");
            }
        }
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    private void viewAvailableNotes(Scanner scanner) {
        System.out.println("\n==================================================");
        System.out.println("              AVAILABLE NOTES");
        System.out.println("==================================================");

        List<NotesSubmission> submissions = submissionDAO.getAllNotes();
        boolean hasApproved = false;

        for (NotesSubmission submission : submissions) {
            if (submission.getApprovalStatus().equalsIgnoreCase("Approved")) {
                hasApproved = true;
                System.out.println("Submission ID : " + submission.getSubmissionId());
                System.out.println("Topic         : " + submission.getRequest().getTopic());
                System.out.println("Uploaded By   : " + submission.getStudent().getFullName());
                System.out.println("File Name     : " + submission.getFileName());
                System.out.println("File Path     : " + submission.getFilePath());
                System.out.println("Approved By   : " + (submission.getAdmin() != null && submission.getAdmin().getAdminId() > 0 ? submission.getAdmin().getName() : "N/A"));
                System.out.println("Upload Date   : " + submission.getUploadDate());
                System.out.println("----------------------------------------------");
            }
        }

        if (!hasApproved) {
            System.out.println("No approved notes available yet.");
        }

        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

}