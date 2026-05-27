package com.jcaa.usersmanagement.domain.exception;

public final class InvalidRangoIdException extends DomainException {

    private static final String MESSAGE = "The rango id cannot be empty.";

    private InvalidRangoIdException(final String message) {
        super(message);
    }

    public static InvalidRangoIdException becauseValueIsEmpty() {
        return new InvalidRangoIdException(MESSAGE);
    }
}
