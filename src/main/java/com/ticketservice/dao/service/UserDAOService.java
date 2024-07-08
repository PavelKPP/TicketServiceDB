package com.ticketservice.dao.service;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserDAOService {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    private final DataSource dataSource;
    private TicketDAOService ticketDAOService;

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

    @Transactional
    public void activateUserById(User id, Long ticketId, String ticketType) {
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update usertable set activated = true where id = ?");
            preparedStatement.setObject(1, id);
            Ticket ticket = new Ticket();
            ticket.setId(ticketId);
            ticket.setUser_id(id);
            ticket.setTicketType(ticketType);
            ticketDAOService.saveTicket(ticket);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
