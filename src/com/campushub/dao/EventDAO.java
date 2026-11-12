package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    private Connection connection;

    public EventDAO() {
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
}