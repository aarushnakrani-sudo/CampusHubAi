package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Admin;
import com.campushub.model.Announcement;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.List;

public class AdminAnnouncementDAO {

    private Connection connection;

    public AdminAnnouncementDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Announcement> getAllAnnouncements() {

        List<Announcement> announcementList = new LinkedList<>();

        String sql =
                "SELECT a.*, ad.name " +
                        "FROM Announcement a " +
                        "JOIN Admin ad ON a.admin_id = ad.admin_id " +
                        "ORDER BY a.publish_date DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));

                Announcement announcement = new Announcement(
                        rs.getInt("announcement_id"),
                        admin,
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("publish_date").toLocalDate().atStartOfDay()
                );

                announcementList.add(announcement);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announcementList;
    }

    public boolean addAnnouncement(int adminId,
                                   String title,
                                   String description) {

        String sql =
                "INSERT INTO Announcement(admin_id,title,description,publish_date) VALUES(?,?,?,CURDATE())";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, adminId);
            ps.setString(2, title);
            ps.setString(3, description);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAnnouncement(int announcementId) {

        String sql =
                "DELETE FROM Announcement WHERE announcement_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, announcementId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}