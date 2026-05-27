package com.jcaa.usersmanagement.domain.exception;

public final class InvalidLineaMilitarException extends DomainException {

    private static final String MESSAGE =
            "The military line '%s' is not valid. Expected: OFICIAL, SUBOFICIAL or RECLUTA.";

    private InvalidLineaMilitarException(final String message) {
        super(message);
    }

    public static InvalidLineaMilitarException becauseValueIsInvalid(final String value) {
        return new InvalidLineaMilitarException(String.format(MESSAGE, value));
    }
}
