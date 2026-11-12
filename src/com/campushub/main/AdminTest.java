package com.campushub.main;

import com.campushub.dao.AdminDAO;
import com.campushub.model.Admin;

public class AdminTest {

    public static void main(String[] args) {

        AdminDAO dao = new AdminDAO();

        Admin admin = dao.loginAdmin(
                "admin@campushub.com",
                "admin123"
        );

        if(admin != null){

            System.out.println(admin);

        }else{

            System.out.println("Invalid Login");

        }

    }

}