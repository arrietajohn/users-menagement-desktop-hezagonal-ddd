package com.jcaa.usersmanagement.domain.exception;

public final class RangoMilitarNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID =
            "The rango militar with id '%s' was not found.";
    private static final String MESSAGE_BY_CODIGO =
            "The rango militar with codigo '%s' was not found.";

    private RangoMilitarNotFoundException(final String message) {
        super(message);
    }

    public static RangoMilitarNotFoundException becauseIdWasNotFound(final String id) {
        return new RangoMilitarNotFoundException(String.format(MESSAGE_BY_ID, id));
    }

    public static RangoMilitarNotFoundException becauseCodigoWasNotFound(final String codigo) {
        return new RangoMilitarNotFoundException(String.format(MESSAGE_BY_CODIGO, codigo));
    }
}