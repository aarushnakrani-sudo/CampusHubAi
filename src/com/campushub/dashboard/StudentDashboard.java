package com.campushub.dashboard;
import com.campushub.service.ClubService;
import com.campushub.service.EventService;
import com.campushub.model.Student;
import com.campushub.service.AttendanceService;
import com.campushub.service.TimetableService;
import com.campushub.service.AnnouncementService;
import com.campushub.service.NotesService;
import com.campushub.service.LostFoundService;
import com.campushub.service.ComplaintService;


import java.util.Scanner;

public class StudentDashboard {

    private final Scanner scanner = new Scanner(System.in);

    public void showDashboard(Student student) {

        while (true) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println(" Welcome, " + student.getFullName());
            System.out.println("==========================================");
            System.out.println("Department : " + student.getDepartment().getDepartmentName());
            System.out.println("Semester   : " + student.getSemester());
            System.out.println("==========================================");

            System.out.println("1. View Attendance");
            System.out.println("2. View Timetable");
            System.out.println("3. View Announcements");
            System.out.println("4. Club Management");
            System.out.println("5. Event Registration");
            System.out.println("6. Lost & Found");
            System.out.println("7. Notes Sharing");
            System.out.println("8. Complaint");
            System.out.println("9. Logout");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    AttendanceService attendanceService = new AttendanceService();
                    attendanceService.showAttendance(student);
                    break;

                case 2:

                    TimetableService timetableService = new TimetableService();
                    timetableService.showTimetable(student);
                    break;

                case 3:
                    AnnouncementService announcementService = new AnnouncementService();
                    announcementService.showAnnouncements();
                    break;

                case 4:

                    ClubService clubService = new ClubService();
                    clubService.showClubs(student);
                    break;

                case 5:

                    EventService eventService = new EventService();
                    eventService.showEvents(student);
                    break;

                case 6:

                    LostFoundService service = new LostFoundService();
                    service.showLostFound(student);
                    break;

                case 7:

                    NotesService notesService = new NotesService();
                    notesService.showNotes(student);
                    break;

                case 8:

                    ComplaintService complaintService = new ComplaintService();
                    complaintService.showComplaints(student);
                    break;

                case 9:
                    return;

                default:
                    System.out.println("\nInvalid Choice!");

            }

        }

    }

}