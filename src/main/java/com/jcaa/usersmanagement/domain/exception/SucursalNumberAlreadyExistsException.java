package com.jcaa.usersmanagement.domain.exception;

public final class SucursalNumberAlreadyExistsException extends DomainException {
    private static final String MESSAGE_SUCURSAL_EXISTS = "La sucursal ya existe";
    private SucursalNumberAlreadyExistsException(String message) {
        super(message);
    }

    public static SucursalNumberAlreadyExistsException becauseSucursalAlreadyExists(final String sucursal) {
        return new SucursalNumberAlreadyExistsException(String.format(MESSAGE_SUCURSAL_EXISTS, sucursal));
    }
}
