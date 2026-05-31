package com.jcaa.usersmanagement.domain.exception;

public final class SucursalAlreadyExistsException extends DomainException {
    private static final String MESSAGE_SUCURSAL_EXISTS = "La sucursal ya existe";
    private SucursalAlreadyExistsException(String message) {
        super(message);
    }

    public static SucursalAlreadyExistsException becauseSucursalAlreadyExists(final String sucursal) {
        return new SucursalAlreadyExistsException(String.format(MESSAGE_SUCURSAL_EXISTS, sucursal));
    }
}
