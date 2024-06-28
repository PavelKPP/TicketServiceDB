package com.ticketservice.dao.service;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import com.ticketservice.dao.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOService {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void saveUser(User user) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement("insert into UserTable (id, name, creation_date) values (?, ?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setTimestamp(3, user.getCreationDateTime());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("something went wrong");
            } else {
                System.out.println("values was successfully inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUserById(int id) {
        connection = ConnectionManager.getConnection();
        try{
            preparedStatement = connection.prepareStatement("select * from UserTable where id = ?");
            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("something went wrong");
            } else {
                System.out.println("values was successfully inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(int id) {
        connection = ConnectionManager.getConnection();
        try{
            preparedStatement = connection.prepareStatement("delete from UserTable where id = ?");
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
