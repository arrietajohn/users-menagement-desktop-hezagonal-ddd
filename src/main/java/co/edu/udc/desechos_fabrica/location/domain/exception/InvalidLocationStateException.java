package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationStateException extends DomainException {

    private static final String MESSAGE_EMPTY = "The location state must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The location state must be at least %d characters long.";

    public InvalidLocationStateException(String message) {
        super(message);
    }

    public static InvalidLocationStateException becauseValueIsEmpty() {
        return new InvalidLocationStateException(MESSAGE_EMPTY);
    }

    public static InvalidLocationStateException becauseLengthIsTooShort(int minimumLength) {
        return new InvalidLocationStateException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
