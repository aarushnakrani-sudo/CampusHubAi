package com.campushub.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/CampusHubAI";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Connection target = createRawConnection();
            if (target == null) return null;

            return (Connection) Proxy.newProxyInstance(
                    DatabaseConnection.class.getClassLoader(),
                    new Class<?>[]{Connection.class},
                    new ReconnectingConnectionHandler(target)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection createRawConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("\n[CRITICAL ERROR] MySQL JDBC Driver not found on the classpath!");
            System.err.println("Make sure 'lib/mysql-connector-j-9.7.0.jar' is added to the project libraries/dependencies.");
            return null;
        } catch (SQLException e) {
            System.err.println("\n[CRITICAL ERROR] Failed to connect to the database at: " + URL);
            System.err.println("Ensure MySQL is running, the database 'CampusHubAI' is created, and user credentials are correct.");
            System.err.println("Error details: " + e.getMessage());
            return null;
        }
    }

    private static class ReconnectingConnectionHandler implements InvocationHandler {
        private Connection target;

        public ReconnectingConnectionHandler(Connection target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (target == null || target.isClosed() || !target.isValid(2)) {
                    Connection newTarget = createRawConnection();
                    if (newTarget != null) {
                        try {
                            if (target != null) target.close();
                        } catch (Exception ignored) {}
                        target = newTarget;
                    }
                }
            } catch (Exception ignored) {
                Connection newTarget = createRawConnection();
                if (newTarget != null) {
                    target = newTarget;
                }
            }

            try {
                return method.invoke(target, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }
    }
}