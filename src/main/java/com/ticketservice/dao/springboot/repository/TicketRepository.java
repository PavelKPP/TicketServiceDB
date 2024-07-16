package com.ticketservice.dao.springboot.repository;


import com.ticketservice.dao.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);
}
