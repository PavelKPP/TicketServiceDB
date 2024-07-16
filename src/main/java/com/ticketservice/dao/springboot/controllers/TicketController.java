package com.ticketservice.dao.springboot.controllers;

import com.ticketservice.dao.model.Ticket;
import com.ticketservice.dao.springboot.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final String thisIsMyFirstConditionalBean;
    private final TicketService ticketService;

    @Autowired
    public TicketController(String thisIsMyFirstConditionalBean, TicketService ticketService) {
        this.thisIsMyFirstConditionalBean = thisIsMyFirstConditionalBean;
        this.ticketService = ticketService;
    }

    @GetMapping("/ticketId")
    public Ticket getTicketById(@PathVariable("ticketId") Long ticketId) {
        System.out.println(thisIsMyFirstConditionalBean);
        return ticketService.getTicketById(ticketId);
    }
}
