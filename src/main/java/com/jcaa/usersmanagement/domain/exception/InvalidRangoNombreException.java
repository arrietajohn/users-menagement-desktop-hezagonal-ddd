package com.jcaa.usersmanagement.domain.exception;

public final class InvalidRangoNombreException extends DomainException {

    private static final String MESSAGE_EMPTY =
            "The rango nombre cannot be empty.";
    private static final String MESSAGE_TOO_SHORT =
            "The rango nombre '%s' is too short. It must be at least 3 characters.";

    private InvalidRangoNombreException(final String message) {
        super(message);
    }

    public static InvalidRangoNombreException becauseValueIsEmpty() {
        return new InvalidRangoNombreException(MESSAGE_EMPTY);
    }

    public static InvalidRangoNombreException becauseValueIsTooShort(final String value) {
        return new InvalidRangoNombreException(String.format(MESSAGE_TOO_SHORT, value));
    }
}