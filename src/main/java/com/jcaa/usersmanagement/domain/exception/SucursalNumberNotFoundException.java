package com.jcaa.usersmanagement.domain.exception;

public final class SucursalNumberNotFoundException extends DomainException {
    private static final String MESSAGE_BY_NUMBER = "El número de la sucursal no fue encontrado";
    private SucursalNumberNotFoundException(String message) {
        super(message);
    }

    public static SucursalNumberNotFoundException becauseNumberWasNotFound(final Integer number){
        return new SucursalNumberNotFoundException(String.format(MESSAGE_BY_NUMBER, number));

    }
}
