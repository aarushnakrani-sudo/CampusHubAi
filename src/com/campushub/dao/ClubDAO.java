package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {

    private Connection connection;

    public ClubDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Club> getAllClubs() {

        List<Club> clubList = new ArrayList<>();

        String sql = "SELECT * FROM Club ORDER BY club_name";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Club club = new Club();

                club.setClubId(rs.getInt("club_id"));
                club.setClubName(rs.getString("club_name"));
                club.setDescription(rs.getString("description"));

                clubList.add(club);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubList;

    }

}