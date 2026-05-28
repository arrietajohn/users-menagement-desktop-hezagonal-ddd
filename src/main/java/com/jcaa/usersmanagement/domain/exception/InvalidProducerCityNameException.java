package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerCityNameException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer city name must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The producer city name must have at least %d characters.";

    private InvalidProducerCityNameException(final String message) {
        super(message);
    }

    public static InvalidProducerCityNameException becauseValueIsEmpty() {
        return new InvalidProducerCityNameException(MESSAGE_EMPTY);
    }

    public static InvalidProducerCityNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidProducerCityNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
