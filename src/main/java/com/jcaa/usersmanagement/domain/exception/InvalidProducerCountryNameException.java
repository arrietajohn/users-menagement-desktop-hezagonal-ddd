package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProducerCountryNameException extends DomainException {

    private static final String MESSAGE_EMPTY = "The producer country name must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The producer country name must have at least %d characters.";

    private InvalidProducerCountryNameException(final String message) {
        super(message);
    }

    public static InvalidProducerCountryNameException becauseValueIsEmpty() {
        return new InvalidProducerCountryNameException(MESSAGE_EMPTY);
    }

    public static InvalidProducerCountryNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidProducerCountryNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
