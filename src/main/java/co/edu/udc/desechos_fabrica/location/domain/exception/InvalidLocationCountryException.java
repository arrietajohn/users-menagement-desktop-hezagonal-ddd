package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationCountryException extends DomainException {

    private static final String MESSAGE_EMPTY = "The location country must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The location country must be at least %d characters long.";

    public InvalidLocationCountryException(String message) {
        super(message);
    }

    public static InvalidLocationCountryException becauseValueIsEmpty() {
        return new InvalidLocationCountryException(MESSAGE_EMPTY);
    }

    public static InvalidLocationCountryException becauseLengthIsTooShort(int minimumLength) {
        return new InvalidLocationCountryException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
