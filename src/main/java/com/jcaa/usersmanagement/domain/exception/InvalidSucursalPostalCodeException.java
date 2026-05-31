package com.jcaa.usersmanagement.domain.exception;

public final class InvalidSucursalPostalCodeException extends DomainException {
    private static final String MESSAGE_EMPTY  = "El código postal no puede ir vacío";
    private static final String MESSAGE_FORMAT = "El código postal debe tener exactamente 6 dígitos numéricos: '%s'";

    public InvalidSucursalPostalCodeException(String message) {
        super(message);
    }

    public static InvalidSucursalPostalCodeException becauseValueIsEmpty() {
        return new InvalidSucursalPostalCodeException(MESSAGE_EMPTY);
    }

    public static InvalidSucursalPostalCodeException becauseFormatIsInvalid(String value) {
        return new InvalidSucursalPostalCodeException(String.format(MESSAGE_FORMAT, value));
    }
}
