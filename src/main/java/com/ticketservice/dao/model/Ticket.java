package com.ticketservice.dao.model;

import org.hibernate.boot.model.relational.SqlStringGenerationContext;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "TicketTable")
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user_id;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    public Ticket() {

    }

    public Ticket(Long id, User user_id, String ticketType) {
        this.id = id;
        this.user_id = user_id;
        this.ticketType = ticketType;
        creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ticket ticket = (Ticket) object;
        return Objects.equals(id, ticket.id) && Objects.equals(user_id, ticket.user_id) && Objects.equals(ticketType, ticket.ticketType) && Objects.equals(creationDate, ticket.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, ticketType, creationDate);
    }
}
