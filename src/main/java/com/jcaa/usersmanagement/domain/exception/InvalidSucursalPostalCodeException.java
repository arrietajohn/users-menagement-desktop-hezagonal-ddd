package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalPostalCodeException extends DomainException {
    private static final String MESSAGE_EMPTY = "El código postal no puede ir vacío";
    public InvalidSucursalPostalCodeException(String message) {
        super(message);
    }

    public static InvalidSucursalPostalCodeException becauseValueIsEmpty() {
        return new InvalidSucursalPostalCodeException(MESSAGE_EMPTY);
    }
}
