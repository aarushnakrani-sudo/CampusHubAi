package com.campushub.database;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Connected to CampusHubAI Database Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }

    }
}
