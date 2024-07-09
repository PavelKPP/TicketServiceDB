package com.ticketservice.dao.exceptions;

public class MethodIsLockedByPropertyException extends RuntimeException{

    public MethodIsLockedByPropertyException(String message) {
        super(message);
    }
}
