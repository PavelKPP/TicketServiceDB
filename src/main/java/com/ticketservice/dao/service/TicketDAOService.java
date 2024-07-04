package com.ticketservice.dao.service;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDAOService {
    private static Connection connection = null;
    PreparedStatement preparedStatement = null;

    private final DataSource dataSource;

    @Autowired
    public TicketDAOService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveTicket(Ticket ticket) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into TicketTable (id, user_id, ticketType, creationDate) values (?, ?, ?, ?)");
            preparedStatement.setLong(1, ticket.getId());
            preparedStatement.setLong(2, ticket.getUser_id().getId());
            preparedStatement.setString(3, ticket.getTicketType());
            preparedStatement.setTimestamp(4, ticket.getCreationDate());

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected == 0) {
                System.out.println("something went wrong");
            } else {
                System.out.println("values was successfully inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTicketByTicketId(int id) {
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from TicketTable where id = ?");
            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected == 0) {
                System.out.println("something went wrong");
            } else {
                System.out.println("values was successfully inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void getTicketByUserId(int id) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from TicketTable where user_id = ?");
            preparedStatement.setLong(1, id);

            //TO BE REFACTORED:
            //create a separated function to check the quantity of affected rows.
            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected == 0) {
                System.out.println("something went wrong");
            } else {
                System.out.println("values was successfully inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTicketType(String typeOfTicketToBeUpdated,
                                 String typeOfTicketValueToBeUpdatedTo) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update TicketTable set ticket_type = ? where ticket_type = ?");
            preparedStatement.setString(1, typeOfTicketToBeUpdated);
            preparedStatement.setString(2, typeOfTicketValueToBeUpdatedTo);

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
}
