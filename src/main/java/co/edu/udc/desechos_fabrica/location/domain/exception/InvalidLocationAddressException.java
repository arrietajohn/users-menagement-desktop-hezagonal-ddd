package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationAddressException extends DomainException {

    private static final String MESSAGE_EMPTY = "The location address must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The location address must have at least %d characters.";

    private InvalidLocationAddressException(String message) {
        super(message);
    }

    public static InvalidLocationAddressException becauseValueIsEmpty(){
        return new InvalidLocationAddressException(MESSAGE_EMPTY);
    }

    public static InvalidLocationAddressException validateMinimumLength(final int minimumLength) {
        return new InvalidLocationAddressException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
