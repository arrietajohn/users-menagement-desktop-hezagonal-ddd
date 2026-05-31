package com.jcaa.usersmanagement.domain.exception;

public class InvalidSucursalCityOperationException extends DomainException {
    private static final String MESSAGE_EMPTY = "El código postal no puede ir vacío";
    public InvalidSucursalCityOperationException(String message) {
        super(message);
    }

    public static InvalidSucursalCityOperationException becauseValueIsEmpty() {
        return  new InvalidSucursalCityOperationException(MESSAGE_EMPTY);
    }
}
