package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class RangoMilitarPersistenceException extends RuntimeException {

    private RangoMilitarPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static RangoMilitarPersistenceException becauseSaveFailed(
            final String id, final Throwable cause) {
        return new RangoMilitarPersistenceException(
                "Failed to save rango militar with id: " + id, cause);
    }

    public static RangoMilitarPersistenceException becauseUpdateFailed(
            final String id, final Throwable cause) {
        return new RangoMilitarPersistenceException(
                "Failed to update rango militar with id: " + id, cause);
    }

    public static RangoMilitarPersistenceException becauseDeleteFailed(
            final String id, final Throwable cause) {
        return new RangoMilitarPersistenceException(
                "Failed to delete rango militar with id: " + id, cause);
    }

    public static RangoMilitarPersistenceException becauseFindByIdFailed(
            final String id, final Throwable cause) {
        return new RangoMilitarPersistenceException(
                "Failed to find rango militar with id: " + id, cause);
    }

    public static RangoMilitarPersistenceException becauseFindByCodigoFailed(
            final String codigo, final Throwable cause) {
        return new RangoMilitarPersistenceException(
                "Failed to find rango militar with codigo: " + codigo, cause);
    }

    public static RangoMilitarPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new RangoMilitarPersistenceException("Failed to retrieve all rangos militares", cause);
    }
}
