package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerNameException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer name must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The producer name must have at least %d characters.";

    private InvalidProducerNameException(final String message) {
        super(message);
    }

    public static InvalidProducerNameException becauseValueIsEmpty() {
        return new InvalidProducerNameException(MESSAGE_EMPTY);
    }

    public static InvalidProducerNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidProducerNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
