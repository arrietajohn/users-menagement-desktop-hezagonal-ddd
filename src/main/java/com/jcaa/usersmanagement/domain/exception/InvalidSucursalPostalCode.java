package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalPostalCode extends RuntimeException {
    private static final String MESSAGE_EMPTY = "El código postal no puede ir vacío";
    public InvalidSucursalPostalCode(String message) {
        super(message);
    }

    public static InvalidSucursalPostalCode becauseValueIsEmpty() {
        return new InvalidSucursalPostalCode(MESSAGE_EMPTY);
    }
}
