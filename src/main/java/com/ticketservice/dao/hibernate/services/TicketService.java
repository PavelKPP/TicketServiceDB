package com.ticketservice.dao.hibernate.services;

import com.ticketservice.dao.hibernate.dao.TicketHibernateDao;
import com.ticketservice.dao.model.Ticket;

import java.util.List;

public class TicketService {

    private TicketHibernateDao ticketHibernateDao = new TicketHibernateDao();

    public TicketService() {

    }

    public Ticket findById(Long id){
        return ticketHibernateDao.findById(id);
    }

    public Ticket findTicketByUserId(Long id) {
        return ticketHibernateDao.findTicketByUserId(id);
    }

    public List<Ticket> findAllTicketsByUserId(Long id) {
        return ticketHibernateDao.findAllTicketsByUserId(id);
    }

    public void addTicket(Ticket ticket) {
        ticketHibernateDao.addTicket(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketHibernateDao.deleteTicket(ticket);
    }

    public void updateTicketByTicketType(Ticket ticket, String ticketTypeToBeChangedTo) {
        ticketHibernateDao.updateTicketByTicketType(ticket, ticketTypeToBeChangedTo);
    }


}
