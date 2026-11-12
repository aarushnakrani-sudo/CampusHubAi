package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminEventDAO {

    private Connection connection;

    public AdminEventDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Event> getAllEvents() {

        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT * FROM Event ORDER BY event_date";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Event event = new Event();

                event.setEventId(rs.getInt("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setDescription(rs.getString("description"));
                event.setVenue(rs.getString("venue"));
                event.setEventDate(rs.getDate("event_date").toLocalDate());

                eventList.add(event);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;

    }

    public boolean addEvent(String name,
                            String description,
                            String venue,
                            Date eventDate) {

        String sql =
                "INSERT INTO Event(event_name,description,venue,event_date) VALUES(?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, venue);
            ps.setDate(4, eventDate);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteEvent(int eventId) {

        String sql = "DELETE FROM Event WHERE event_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, eventId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public int getParticipantCount(int eventId) {
        String sql = "SELECT COUNT(*) FROM EventRegistration WHERE event_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Database error getting participant count: " + e.getMessage());
        }
        return 0;
    }

}