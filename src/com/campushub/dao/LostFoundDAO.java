package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.LostFound;
import com.campushub.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LostFoundDAO {

    private Connection connection;

    public LostFoundDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<LostFound> getAllItems() {

        List<LostFound> list = new ArrayList<>();

        String sql =
                "SELECT lf.*, s.full_name " +
                        "FROM LostFound lf " +
                        "JOIN Student s ON lf.student_id = s.student_id " +
                        "WHERE lf.status = 'Active' " +
                        "ORDER BY lf.item_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));

                LostFound item = new LostFound();

                item.setItemId(rs.getInt("item_id"));
                item.setStudent(student);
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setType(rs.getString("type"));
                item.setStatus(rs.getString("status"));
                item.setImagePath(rs.getString("image_path"));

                list.add(item);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addItem(int studentId,
                           String itemName,
                           String description,
                           String location,
                           String type,
                           String imagePath) {

        String sql =
                "INSERT INTO LostFound(student_id,item_name,description,location,type,status,report_date,image_path) " +
                "VALUES(?,?,?,?,?,'Active',CURDATE(),?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setString(2, itemName);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setString(5, type);
            ps.setString(6, imagePath);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}