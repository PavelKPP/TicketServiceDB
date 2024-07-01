package com.ticketservice.dao.hibernate.dao;

import com.ticketservice.dao.hibernate.hibernateutil.SessionFactoryProvider;
import com.ticketservice.dao.model.Ticket;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketHibernateDao {


    public Ticket findById(Long id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(Ticket.class, id);
    }

    public Ticket findTicketByUserId(Long id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Ticket t where t.user_id =: user_id", Ticket.class);
        query.setParameter("user_id", id);
        Ticket ticket = (Ticket) query.uniqueResult();
        session.close();
        return ticket;
    }

    public List<Ticket> findAllTicketsByUserId(Long id){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Ticket t where t.user_id =: user_id", Ticket.class);
        query.setParameter("user_id", id);
        query.executeUpdate();
        session.close();
        return query.getResultList();
    }



    public void addTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    public void deleteTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(ticket);
        transaction.commit();
        session.close();
    }

     public void updateTicketByTicketType(Ticket ticket, String ticketTypeToBeChangedTo) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticketToBeChanged = findById(ticket.getId());
        ticketToBeChanged.setTicketType(ticketTypeToBeChangedTo);
        session.saveOrUpdate(ticketToBeChanged);
        transaction.commit();
        session.close();
     }
}
