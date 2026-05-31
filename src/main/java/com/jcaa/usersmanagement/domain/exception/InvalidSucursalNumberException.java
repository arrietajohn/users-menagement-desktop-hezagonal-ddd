package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalNumberException extends DomainException {
    private static final String MESSAGE_EMPTY = "En número de la sucursal no puede ir vacío";
    private static final String MESSAGE_INVALIDFORMAT = "El número de la sucursal no puede contener letras";
    public InvalidSucursalNumberException(String message) {
        super(message);
    }

    public static InvalidSucursalNumberException becauseValueIsEmpty() {
        return new InvalidSucursalNumberException(MESSAGE_EMPTY);
    }
    public static InvalidSucursalNumberException becauseValueIsInvalidFormat(final String normalizedValue) {
        return new InvalidSucursalNumberException(String.format(MESSAGE_INVALIDFORMAT, normalizedValue));
    }
}
