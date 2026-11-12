package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.utils.FileManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentNotesSubmissionDAO {

    private Connection connection;

    public StudentNotesSubmissionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // ==========================================
    // Check whether request is approved
    // ==========================================

    public boolean isRequestApproved(int requestId) {

        String sql =
                "SELECT status FROM NotesRequest WHERE request_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, requestId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getString("status")
                        .equalsIgnoreCase("Approved");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================================
    // Upload Notes
    // ==========================================

    public boolean uploadNotes(int requestId,
                               int studentId,
                               String pdfPath) {

        String newPath = FileManager.copyFile(pdfPath, "uploads/notes");

        if (newPath == null) {

            System.out.println("\nPDF File Not Found.");
            return false;

        }

        String fileName = new java.io.File(newPath).getName();

        String sql =
                "INSERT INTO NotesSubmission " +
                        "(request_id, student_id, admin_id, file_name, file_path, approval_status, upload_date) " +
                        "VALUES (?, ?, NULL, ?, ?, 'Pending', CURDATE())";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, requestId);
            ps.setInt(2, studentId);
            ps.setString(3, fileName);
            ps.setString(4, newPath);

            if (ps.executeUpdate() > 0) {

                FileManager.writeLog(
                        "Student " + studentId +
                                " uploaded notes : " + fileName
                );

                return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;



    }

}