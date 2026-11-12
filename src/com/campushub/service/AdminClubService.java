package com.campushub.service;

import com.campushub.dao.AdminClubDAO;
import com.campushub.model.Club;

import java.util.List;
import java.util.Scanner;

public class AdminClubService {

    private AdminClubDAO clubDAO = new AdminClubDAO();

    public void manageClubs() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("             CLUB MANAGEMENT");
            System.out.println("==================================================");

            List<Club> clubs = clubDAO.getAllClubs();

            if (clubs.isEmpty()) {

                System.out.println("No Clubs Available.");

            } else {

                for (Club club : clubs) {

                    System.out.println("Club ID     : " + club.getClubId());
                    System.out.println("Club Name   : " + club.getClubName());
                    System.out.println("Description : " + club.getDescription());
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println("\n1. Add Club");
            System.out.println("2. Delete Club");
            System.out.println("3. View Club Members");
            System.out.println("4. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Club Name : ");
                    String name = scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    if (clubDAO.addClub(name, description)) {

                        System.out.println("\nClub Added Successfully.");

                    } else {

                        System.out.println("\nUnable to Add Club.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Club ID : ");
                    int clubId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    boolean clubExists = false;
                    for (Club club : clubs) {
                        if (club.getClubId() == clubId) {
                            clubExists = true;
                            break;
                        }
                    }
                    if (!clubExists) {
                        System.out.println("\nInvalid Club ID! Please select a valid club from the list.");
                        break;
                    }

                    if (clubDAO.deleteClub(clubId)) {

                        System.out.println("\nClub Deleted Successfully.");

                    } else {

                        System.out.println("\nClub Not Found.");

                    }

                    break;

                case 3:

                    System.out.print("Enter Club ID to View Members: ");
                    int viewClubId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    boolean viewClubExists = false;
                    for (Club club : clubs) {
                        if (club.getClubId() == viewClubId) {
                            viewClubExists = true;
                            break;
                        }
                    }
                    if (!viewClubExists) {
                        System.out.println("\nInvalid Club ID! Please select a valid club from the list.");
                        break;
                    }

                    List<com.campushub.model.Student> members = clubDAO.getClubMembers(viewClubId);
                    System.out.println("\n==================================================");
                    System.out.println("               CLUB MEMBERS DETAILS");
                    System.out.println("==================================================");
                    if (members.isEmpty()) {
                        System.out.println("No students have joined this club yet.");
                    } else {
                        System.out.printf("%-12s | %-15s | %-25s | %-30s%n", "Student ID", "Enrollment No", "Full Name", "Email");
                        System.out.println("--------------------------------------------------------------------------------------");
                        for (com.campushub.model.Student s : members) {
                            System.out.printf("%-12d | %-15s | %-25s | %-30s%n", s.getStudentId(), s.getEnrollmentNo(), s.getFullName(), s.getCollegeEmail());
                        }
                    }
                    System.out.println("\nPress ENTER to continue...");
                    scanner.nextLine();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}