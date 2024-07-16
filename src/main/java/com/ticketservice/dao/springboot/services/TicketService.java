package com.ticketservice.dao.springboot.services;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.springboot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;


    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("No Such Ticket"));
    }

    public List<Ticket> getTicketByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public void updateTicketType(Long ticketId, String ticketType) {
        Ticket ticketForUpdate = getTicketById(ticketId);
        ticketForUpdate.setTicketType(ticketType);
        ticketRepository.save(ticketForUpdate);
    }

    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
