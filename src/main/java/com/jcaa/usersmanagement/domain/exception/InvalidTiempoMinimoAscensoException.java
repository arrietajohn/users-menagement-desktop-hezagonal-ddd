package com.jcaa.usersmanagement.domain.exception;

public final class InvalidTiempoMinimoAscensoException extends DomainException {

    private static final String MESSAGE =
            "The tiempo minimo de ascenso '%d' is not valid. It must be zero or positive.";

    private InvalidTiempoMinimoAscensoException(final String message) {
        super(message);
    }

    public static InvalidTiempoMinimoAscensoException becauseValueIsNegative(final int value) {
        return new InvalidTiempoMinimoAscensoException(String.format(MESSAGE, value));
    }
}