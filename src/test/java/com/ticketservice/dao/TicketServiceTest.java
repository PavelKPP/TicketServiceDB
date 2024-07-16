package com.ticketservice.dao;

import com.sun.tools.javac.util.List;
import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.repository.TicketRepository;
import com.ticketservice.dao.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    private static TicketRepository ticketRepository;
    private static TicketService ticketService;

    @BeforeAll
    public static void init() {
        ticketRepository = mock(TicketRepository.class);
        ticketService = new TicketService(ticketRepository);
    }


    @Test
    public void saveTicket_SavedExpected() {
        Ticket ticketToBeSaved = new Ticket();

        Mockito.when(ticketRepository.save(ticketToBeSaved)).thenReturn(ticketToBeSaved);

        Ticket savedTicket = ticketService.saveTicket(ticketToBeSaved);
        Assertions.assertEquals(ticketToBeSaved, savedTicket);
    }

    @Test
    public void saveTicket_NullTicket_DataIntegrityExceptionExpected() {
        Ticket ticket = null;
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> ticketService.saveTicket(ticket));
    }

    @Test
    public void saveTicket_WithNonExistingUser_NotSavedExpected() {
        Ticket ticketToBeSaved = new Ticket();
        ticketToBeSaved.setUser_id(1239L);

        Mockito.when(ticketRepository.save(ticketToBeSaved)).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> ticketService.saveTicket(ticketToBeSaved));
    }


    @Test
    public void getTicketById_AlreadyExistingId_TicketFoundExpected() {
        Long id = 1L;
        Assertions.assertEquals(ticketRepository.findById(id), ticketService.getTicketById(id));
    }

    @Test
    public void getTicketByID_NullId_EntityNotFoundExpected() {
        Long id = null;
        Assertions.assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketById(id));
    }

    @Test
    public void getTicketById_NotExistingId_EntityNotFoundExceptionExpected() {
        Long notExistingId = 2342342L;
        when(ticketRepository.findById(notExistingId)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketById(notExistingId));
    }

    @Test
    public void getTicketByUserId_ValidId_TicketsReturnedExpected() {
        Ticket ticketsToBeFound = new Ticket();
        ticketsToBeFound.setUser_id(1L);

        when(ticketRepository.findByUserId(ticketsToBeFound.getUser_id())).thenReturn(List.of(ticketsToBeFound));
        List<Ticket> ticketsList = ticketService.getTicketByUserId(ticketsToBeFound.getUser_id());

        Assertions.assertEquals(ticketsList, List.of(ticketsToBeFound));
    }

    @Test
    public void getTicketByUserId_UserIdIsNull_DataIntegrityVialationExpected() {
        Ticket ticketToBeFound = new Ticket();
        ticketToBeFound.setUser_id(null);

        when(ticketRepository.findById(ticketToBeFound.getUser_id())).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> ticketService.getTicketByUserId(ticketToBeFound.getUser_id()));
    }

    @Test
    public void getTicketByUserId_NotValidId_TicketsIsNotReturnedExpected() {
        Ticket ticketsToBeFound = new Ticket();
        ticketsToBeFound.setUser_id(12312L);

        when(ticketRepository.findByUserId(ticketsToBeFound.getUser_id())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketByUserId(ticketsToBeFound.getUser_id()));
    }

    @Test
    public void deleteTicketById_ValidId_TicketDeletedExpected() {
        Long validId = 1L;
        Assertions.assertDoesNotThrow(EntityNotFoundException.class, () -> ticketService.getTicketById(validId));
    }

    @Test
    public void deleteTicketById_NotValidId_EntityNotFoundExpected() {
        Long notValidId = 12312L;
        Assertions.assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketById(notValidId));
    }

}
