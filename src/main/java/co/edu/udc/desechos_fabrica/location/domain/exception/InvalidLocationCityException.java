package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationCityException extends DomainException {

    private static final String MESSAGE_EMPTY = "The location city must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The location city must be at least %d characters long.";

    public InvalidLocationCityException(String message) {
        super(message);
    }

    public static InvalidLocationCityException becauseValueIsEmpty() {
        return new InvalidLocationCityException(MESSAGE_EMPTY);
    }

    public static InvalidLocationCityException becauseLengthIsTooShort(int minimumLength) {
        return new InvalidLocationCityException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
