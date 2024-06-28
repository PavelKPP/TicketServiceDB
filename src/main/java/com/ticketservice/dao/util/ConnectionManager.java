package com.ticketservice.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private static String driver = "org.postgresql.Driver";
    private static String username = "postgres";
    private static String password = "password";
    private static Connection connection;
    private static String urlString;

    public static Connection getConnection() {
        try{
            Class.forName(driver);
            try{
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("connection success");
            } catch (SQLException ex){
                System.out.println("Failed to create a db connection");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
        return connection;
    }
}
