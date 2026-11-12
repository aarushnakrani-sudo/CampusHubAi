package com.campushub.service;
import com.campushub.dashboard.StudentDashboard;
import com.campushub.dashboard.AdminDashboard;
import com.campushub.dao.AdminDAO;
import com.campushub.dao.StudentDAO;
import com.campushub.model.Admin;
import com.campushub.model.Student;

import java.util.Scanner;

public class LoginService {

    private final Scanner scanner = new Scanner(System.in);

    private final StudentDAO studentDAO = new StudentDAO();
    private final AdminDAO adminDAO = new AdminDAO();

    // ==========================
    // Student Login
    // ==========================

    public void studentLogin() {

        System.out.println("\n========== Student Login ==========");

        System.out.print("College Email : ");
        String email = scanner.nextLine();

        System.out.print("Password      : ");
        String password = scanner.nextLine();

        Student student = studentDAO.loginStudent(email, password);

        if (student != null) {

            StudentDashboard dashboard = new StudentDashboard();
            dashboard.showDashboard(student);

        } else {

            System.out.println("\nInvalid Email or Password!");

        }

    }

    // ==========================
    // Admin Login
    // ==========================

    public void adminLogin() {

        System.out.println("\n========== Admin Login ==========");

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Password : ");
        String password = scanner.nextLine();

        Admin admin = adminDAO.loginAdmin(email, password);

        if (admin != null) {

            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, " + admin.getName());

            AdminDashboard dashboard = new AdminDashboard();
            dashboard.showDashboard(admin);

        } else {

            System.out.println("\nInvalid Email or Password!");

        }

    }
}