package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Admin;
import com.campushub.model.Announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.List;

public class AnnouncementDAO {

    private Connection connection;

    public AnnouncementDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Announcement> getAllAnnouncements() {

        List<Announcement> announcementList = new LinkedList<>();


        String sql =
                "SELECT a.announcement_id, a.title, a.description, a.publish_date, " +
                        "ad.admin_id, ad.name " +
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

                LocalDateTime publishDate =
                        rs.getDate("publish_date").toLocalDate().atStartOfDay();

                Announcement announcement = new Announcement(
                        rs.getInt("announcement_id"),
                        admin,
                        rs.getString("title"),
                        rs.getString("description"),
                        publishDate
                );

                announcementList.add(announcement);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announcementList;
    }
}