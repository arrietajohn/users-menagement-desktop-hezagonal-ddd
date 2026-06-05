package com.jcaa.usersmanagement.domain.exception;

public final class RouteNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "The route with id '%s' was not found.";

    private RouteNotFoundException(final String message) {
        super(message);
    }

    public static RouteNotFoundException becauseIdWasNotFound(final String routeId) {
        return new RouteNotFoundException(String.format(MESSAGE_BY_ID, routeId));
    }
}