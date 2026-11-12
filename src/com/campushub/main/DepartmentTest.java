package com.campushub.main;

import com.campushub.dao.DepartmentDAO;
import com.campushub.model.Department;

import java.util.List;

public class DepartmentTest {

    public static void main(String[] args) {

        DepartmentDAO dao = new DepartmentDAO();

        List<Department> departments = dao.getAllDepartments();

        for (Department department : departments) {
            System.out.println(department);
        }

    }

}