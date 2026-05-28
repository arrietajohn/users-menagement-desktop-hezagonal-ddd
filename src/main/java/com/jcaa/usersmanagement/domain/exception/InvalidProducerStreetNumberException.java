package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerStreetNumberException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer street number must not be empty.";

    private InvalidProducerStreetNumberException(final String message) {
        super(message);
    }

    public static InvalidProducerStreetNumberException becauseValueIsEmpty() {
        return new InvalidProducerStreetNumberException(MESSAGE_EMPTY);
    }
}
