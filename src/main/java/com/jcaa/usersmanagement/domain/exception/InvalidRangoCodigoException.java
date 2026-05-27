package com.jcaa.usersmanagement.domain.exception;

public final class InvalidRangoCodigoException extends DomainException {

    private static final String MESSAGE_EMPTY =
            "The rango codigo cannot be empty.";
    private static final String MESSAGE_FORMAT =
            "The rango codigo '%s' is not valid. It must be 2-10 alphanumeric characters (A-Z, 0-9).";

    private InvalidRangoCodigoException(final String message) {
        super(message);
    }

    public static InvalidRangoCodigoException becauseValueIsEmpty() {
        return new InvalidRangoCodigoException(MESSAGE_EMPTY);
    }

    public static InvalidRangoCodigoException becauseFormatIsInvalid(final String value) {
        return new InvalidRangoCodigoException(String.format(MESSAGE_FORMAT, value));
    }
}