package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidLocationCoordinateException extends DomainException{

    private static final String MESSAGE_EMPTY = "The location coordinate must not be empty.";
    private static final String MESSAGE_INVALID_FORMAT = "Latitude must be numbers between -90 and 90, longitude must be numbers between -180 and 180.";


    private InvalidLocationCoordinateException(final String message) {
        super(message);
    }

    public static InvalidLocationCoordinateException becauseValueIsEmpty() {
        return new InvalidLocationCoordinateException(MESSAGE_EMPTY);
    }

    public static InvalidLocationCoordinateException becauseInvalidFormat() {
        return new InvalidLocationCoordinateException(MESSAGE_INVALID_FORMAT);
    }
}
