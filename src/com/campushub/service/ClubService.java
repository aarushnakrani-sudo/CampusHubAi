package com.campushub.service;

import com.campushub.dao.ClubDAO;
import com.campushub.dao.ClubMembershipDAO;
import com.campushub.model.Club;
import com.campushub.model.Student;

import java.util.List;
import java.util.Scanner;

public class ClubService {

    private ClubDAO clubDAO = new ClubDAO();
    private ClubMembershipDAO membershipDAO = new ClubMembershipDAO();

    public void showClubs(Student student) {

        List<Club> clubs = clubDAO.getAllClubs();

        if (clubs.isEmpty()) {
            System.out.println("\nNo Clubs Available.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("               COLLEGE CLUBS");
        System.out.println("==============================================");

        for (Club club : clubs) {

            System.out.println(club.getClubId() + ". " + club.getClubName());
            System.out.println("   " + club.getDescription());
            System.out.println();

        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Club ID to Join (0 to Back): ");
        int clubId = com.campushub.utils.ConsoleUtils.readInt(scanner);

        if (clubId == 0)
            return;

        boolean clubExists = false;
        for (Club club : clubs) {
            if (club.getClubId() == clubId) {
                clubExists = true;
                break;
            }
        }

        if (!clubExists) {
            System.out.println("\nInvalid Club ID! Please select a valid club from the list.");
            return;
        }

        if (membershipDAO.isAlreadyMember(student.getStudentId(), clubId)) {

            System.out.println("\nYou are already a member of this club.");

        } else {

            if (membershipDAO.joinClub(student.getStudentId(), clubId)) {

                System.out.println("\nSuccessfully Joined the Club!");

            } else {

                System.out.println("\nUnable to Join Club.");

            }

        }

    }

}