package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNumberSucursalException extends DomainException {
    private static final String MESSAGE_EMPTY = "En número de la sucursal no puede ir vacío";
    private static final String MESSAGE_INVALIDFORMAT = "El número de la sucursal no puede contener letras";
    public InvalidNumberSucursalException(String message) {
        super(message);
    }

    public InvalidNumberSucursalException becauseValueIsEmpty() {
        return new InvalidNumberSucursalException(MESSAGE_EMPTY);
    }
    public InvalidNumberSucursalException becauseValueIsInvalidFormat() {
        return new InvalidNumberSucursalException(MESSAGE_INVALIDFORMAT);
    }
}
