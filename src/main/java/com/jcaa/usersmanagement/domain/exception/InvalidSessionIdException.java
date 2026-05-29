package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSessionIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "The session id must not be empty.";

    private InvalidSessionIdException(final String message) {
        super(message);
    }

    public static InvalidSessionIdException becauseValueIsEmpty() {
        return new InvalidSessionIdException(MESSAGE_EMPTY);
    }
}