package com.jcaa.usersmanagement.domain.exception;

public final class SucursalNotFoundException extends DomainException {
    private static final String MESSAGE_BY_NUMBER = "La sucursal no fue encontrada";
    private SucursalNotFoundException(String message) {
        super(message);
    }

    public static SucursalNotFoundException becauseIdWasNotFound(final String id){
        return new SucursalNotFoundException(String.format(MESSAGE_BY_NUMBER, id));

    }
}
