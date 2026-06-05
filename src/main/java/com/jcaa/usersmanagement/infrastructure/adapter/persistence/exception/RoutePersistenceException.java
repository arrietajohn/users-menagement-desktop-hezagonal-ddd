package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class RoutePersistenceException extends RuntimeException {

    private static final String MESSAGE_SAVE = "Failed to save route with ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Failed to update route with ID: '%s'.";
    private static final String MESSAGE_FIND = "Failed to find route with ID: '%s'.";
    private static final String MESSAGE_ALL = "Failed to retrieve all routes.";
    private static final String MESSAGE_DELETE = "Failed to delete route with ID: '%s'.";

    private RoutePersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static RoutePersistenceException becauseSaveFailed(
            final String routeId, final Throwable cause) {
        return new RoutePersistenceException(
                String.format(MESSAGE_SAVE, routeId),
                cause);
    }

    public static RoutePersistenceException becauseUpdateFailed(
            final String routeId, final Throwable cause) {
        return new RoutePersistenceException(
                String.format(MESSAGE_UPDATE, routeId),
                cause);
    }

    public static RoutePersistenceException becauseFindByIdFailed(
            final String routeId, final Throwable cause) {
        return new RoutePersistenceException(
                String.format(MESSAGE_FIND, routeId),
                cause);
    }

    public static RoutePersistenceException becauseFindAllFailed(
            final Throwable cause) {
        return new RoutePersistenceException(
                MESSAGE_ALL,
                cause);
    }

    public static RoutePersistenceException becauseDeleteFailed(
            final String routeId, final Throwable cause) {
        return new RoutePersistenceException(
                String.format(MESSAGE_DELETE, routeId),
                cause);
    }
}