package com.ticketservice.dao.service;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import com.ticketservice.dao.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDAOService {
    private static Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void saveTicket(Ticket ticket) {
        connection = ConnectionManager.getConnection();
        try {
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
        connection = ConnectionManager.getConnection();
        try{
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
        connection = ConnectionManager.getConnection();
        try {
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
