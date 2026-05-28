package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer id must not be empty.";

    private InvalidProducerIdException(final String message) {
        super(message);
    }

    public static InvalidProducerIdException becauseValueIsEmpty() {
        return new InvalidProducerIdException(MESSAGE_EMPTY);
    }
}
