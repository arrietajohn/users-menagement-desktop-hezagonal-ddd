package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalDirectionException extends RuntimeException {
    private static final String MESSAGE_EMPTY = "La dirección no puede ser vacía";
    public InvalidSucursalDirectionException(String message) {
        super(message);
    }

    public static InvalidSucursalDirectionException becauseValueIsEmpty() {
        return new InvalidSucursalDirectionException(MESSAGE_EMPTY);
    }
}
