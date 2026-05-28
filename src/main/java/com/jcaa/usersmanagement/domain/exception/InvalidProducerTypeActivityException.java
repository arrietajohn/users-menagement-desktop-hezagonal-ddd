package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerTypeActivityException extends DomainException {

    private static final String MESSAGE_INVALID = "The producer type activity '%s' is not valid.";

    private InvalidProducerTypeActivityException(final String message) {
        super(message);
    }

    public static InvalidProducerTypeActivityException becauseValueIsInvalid(
            final String typeActivity) {
        return new InvalidProducerTypeActivityException(String.format(MESSAGE_INVALID, typeActivity));
    }
}
