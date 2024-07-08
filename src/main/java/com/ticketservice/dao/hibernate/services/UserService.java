package com.ticketservice.dao.hibernate.services;

import com.ticketservice.dao.hibernate.dao.UserHibernateDao;
import com.ticketservice.dao.model.User;

public class UserService {

    private UserHibernateDao userHibernateDao = new UserHibernateDao();

    public UserService() {

    }

    public User findById(Long id) {
        return userHibernateDao.findById(id);
    }

    public void addUser(User user) {
        userHibernateDao.addUser(user);
    }

    public void deleteUser(User user) {
        userHibernateDao.deleteUser(user);
    }
}
