package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSalaIDException extends DomainException {

    private static final String MESSAGE_EMPTY = "The sala id must not be empty.";

    private InvalidSalaIDException(final String message) {
        super(message);
    }

    public static InvalidSalaIDException becauseValueIsEmpty() {
        return new InvalidSalaIDException(MESSAGE_EMPTY);
    }
}