package com.jcaa.usersmanagement.domain.exception;

public final class RangoMilitarAlreadyExistsException extends DomainException {

    private static final String MESSAGE =
            "The rango militar with codigo '%s' already exists.";

    private RangoMilitarAlreadyExistsException(final String message) {
        super(message);
    }

    public static RangoMilitarAlreadyExistsException becauseCodigoAlreadyExists(
            final String codigo) {
        return new RangoMilitarAlreadyExistsException(String.format(MESSAGE, codigo));
    }
}
