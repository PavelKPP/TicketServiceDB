package com.ticketservice.dao;

import com.ticketservice.dao.service.TicketDAOService;
import com.ticketservice.dao.service.UserDAOService;
import com.ticketservice.dao.spring.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpringMainClass {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        TicketDAOService ticketDAOService = applicationContext.getBean(TicketDAOService.class);
        UserDAOService userDAOService = applicationContext.getBean(UserDAOService.class);

        ticketDAOService.getTicketByTicketId(1);
    }
}
