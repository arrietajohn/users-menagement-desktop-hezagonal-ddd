package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationStatusException extends DomainException {

    private static final String MESSAGE = "The location status %s is not valid.";

    public InvalidLocationStatusException(String message) {
        super(message);
    }

    public static InvalidLocationStatusException becauseValueIsInvalid(final String status) {
        return new InvalidLocationStatusException(String.format(MESSAGE, status));
    }

}
