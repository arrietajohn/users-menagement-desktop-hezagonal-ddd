package com.jcaa.usersmanagement.domain.exception;

public class InvalidIdBanco extends RuntimeException {
    private static final String MESSAGE_EMPTY = "El id del banco no puede ir vacío";
    public InvalidIdBanco(String message) {
        super(message);
    }

    public static InvalidIdBanco becauseValueIsEmpty(){
        return new InvalidIdBanco(MESSAGE_EMPTY);
    }
}
