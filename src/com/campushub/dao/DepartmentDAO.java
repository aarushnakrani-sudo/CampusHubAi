package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private Connection connection;

    public DepartmentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Insert Department
    public boolean addDepartment(Department department) {

        String sql = "INSERT INTO Department (department_name, department_code) VALUES (?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, department.getDepartmentName());
            ps.setString(2, department.getDepartmentCode());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Departments
    public List<Department> getAllDepartments() {

        List<Department> departmentList = new ArrayList<>();

        String sql = "SELECT * FROM Department";

        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                Department department = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_code")
                );

                departmentList.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentList;
    }

    // Get Department By ID
    public Department getDepartmentById(int id) {

        String sql = "SELECT * FROM Department WHERE department_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_code")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}