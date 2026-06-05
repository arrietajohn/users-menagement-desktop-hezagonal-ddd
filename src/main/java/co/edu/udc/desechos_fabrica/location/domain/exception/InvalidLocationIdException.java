package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationIdException extends DomainException{

    private static final String MESSAGE_EMPTY = "The location id must not be empty.";

    private InvalidLocationIdException(final String message) {
        super(message);
    }

    public static InvalidLocationIdException becauseValueIsEmpty() {
        return new InvalidLocationIdException(MESSAGE_EMPTY);
    }

    public static InvalidLocationIdException becauseInvalidFormat() {
        return new InvalidLocationIdException("The location id must contain only digits.");
    }

}
