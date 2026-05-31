package com.jcaa.usersmanagement.domain.exception;

public class InvalidSucursalCityOperationException extends DomainException {
    private static final String MESSAGE_EMPTY = "La ciudad donde opera la sucursal no puede ir vacía";
    public InvalidSucursalCityOperationException(String message) {
        super(message);
    }

    public static InvalidSucursalCityOperationException becauseValueIsEmpty() {
        return  new InvalidSucursalCityOperationException(MESSAGE_EMPTY);
    }
}
