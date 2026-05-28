package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerPostalNumberException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer postal number must not be empty.";

    private InvalidProducerPostalNumberException(final String message) {
        super(message);
    }

    public static InvalidProducerPostalNumberException becauseValueIsEmpty() {
        return new InvalidProducerPostalNumberException(MESSAGE_EMPTY);
    }
}
