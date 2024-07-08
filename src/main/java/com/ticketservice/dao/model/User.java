package com.ticketservice.dao.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "UserTable")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(name = "creation_date")
    private Timestamp creationDateTime;
    @Column
    private boolean activated;

    public User(){

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        creationDateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(creationDateTime, user.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDateTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
