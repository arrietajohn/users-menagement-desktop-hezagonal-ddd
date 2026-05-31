package com.jcaa.usersmanagement.domain.exception;

public class InvalidIdBanco extends RuntimeException {
    private static final String MESSAGE_EMPTY = "El id del banco no puede ir vacío";
    private static final String MESSAGE_FORMAT = "El id del banco debe ser un número entero positivo: '%s'";

    public InvalidIdBanco(String message) {
        super(message);
    }

    public static InvalidIdBanco becauseValueIsEmpty() {
        return new InvalidIdBanco(MESSAGE_EMPTY);
    }

    public static InvalidIdBanco becauseValueIsNotNumeric(String value) {
        return new InvalidIdBanco(String.format(MESSAGE_FORMAT, value));
    }
}
