package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class SucursalPersistenceException extends RuntimeException {

    private static final String MESSAGE_SAVE = "Failed to save sucursal with ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Failed to update sucursal with ID: '%s'.";
    private static final String MESSAGE_FIND = "Failed to find sucursal with ID: '%s'.";
    private static final String MESSAGE_DELETE = "Failed to delete sucursal with ID: '%s'.";

    private SucursalPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static SucursalPersistenceException becauseSaveFailed(final String id, final Throwable cause) {
        return new SucursalPersistenceException(String.format(MESSAGE_SAVE, id), cause);
    }

    public static SucursalPersistenceException becauseUpdateFailed(final String id, final Throwable cause) {
        return new SucursalPersistenceException(String.format(MESSAGE_UPDATE, id), cause);
    }

    public static SucursalPersistenceException becauseFindByIdFailed(final String id, final Throwable cause) {
        return new SucursalPersistenceException(String.format(MESSAGE_FIND, id), cause);
    }

    public static SucursalPersistenceException becauseDeleteFailed(final String id, final Throwable cause) {
        return new SucursalPersistenceException(String.format(MESSAGE_DELETE, id), cause);
    }
}