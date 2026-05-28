package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerEntityTypeException extends DomainException {

    private static final String MESSAGE_INVALID = "The producer entity type '%s' is not valid.";

    private InvalidProducerEntityTypeException(final String message) {
        super(message);
    }

    public static InvalidProducerEntityTypeException becauseValueIsInvalid(final String entityType) {
        return new InvalidProducerEntityTypeException(String.format(MESSAGE_INVALID, entityType));
    }
}
