package com.campushub.main;

import com.campushub.dao.AdminDAO;
import com.campushub.model.Admin;
import com.campushub.service.AdminNotesService;

public class AdminNotesTest {

    public static void main(String[] args) {

        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.loginAdmin("admin@campushub.com", "admin123");
        if (admin == null) {
            admin = new Admin();
            admin.setAdminId(1);
            admin.setName("System Administrator");
            admin.setEmail("admin@campushub.com");
        }

        AdminNotesService service = new AdminNotesService();
        service.manageNotes(admin);

    }

}