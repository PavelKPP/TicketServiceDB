package com.ticketservice.dao.service;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOService {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    private final DataSource dataSource;

    @Autowired
    public UserDAOService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveUser(User user) {
        try {
            connection = dataSource.getConnection();
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
        try{
            connection = dataSource.getConnection();
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
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from UserTable where id = ?");
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
