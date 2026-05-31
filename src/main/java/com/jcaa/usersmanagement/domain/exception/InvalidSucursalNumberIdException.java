package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalNumberIdException extends DomainException {
    private static final String MESSAGE_EMPTY = "El id de la sucursal no puede ir vacío";
    public InvalidSucursalNumberIdException(String message) {
        super(message);
    }

    public static InvalidSucursalNumberIdException becauseValueIsEmpty() {
        return new InvalidSucursalNumberIdException(MESSAGE_EMPTY);
    }
}
