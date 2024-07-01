package com.ticketservice.dao.hibernate.dao;

import com.ticketservice.dao.hibernate.hibernateutil.SessionFactoryProvider;
import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;


public class UserHibernateDao {

    private TicketHibernateDao ticketHibernateDao;

    public User findById(Long id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    public void addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void updateUserNameByIdAndTicketByTicketType(Long id, String nameToChange, String ticketTypeToBeChangedFor) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query userQuery = session.createQuery("update User set name =: nameToChange where id =: id", User.class);
        userQuery.setParameter("nameToChange", nameToChange);
        userQuery.setParameter("id", id);
        userQuery.executeUpdate();

        List<Ticket> ticketsListOfCurrentUser = ticketHibernateDao.findAllTicketsByUserId(id);

        Iterator<Ticket> it = ticketsListOfCurrentUser.iterator();
        Query ticketQuery;
        while(it.hasNext()) {
            ticketQuery = session.createQuery("update Ticket  set ticketType =: ticketTypeToBeChangedFor", Ticket.class);
            ticketQuery.setParameter("ticketTypeToBeChangedFor", ticketTypeToBeChangedFor);
            ticketQuery.executeUpdate();
        }
        session.close();
    }
}
