package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class LocationAlreadyExistException extends DomainException {

    private static final String MESSAGE = "Location with id '%s' already exists.";

    public LocationAlreadyExistException(String message) {
        super(message);
    }

    public static LocationAlreadyExistException becauseIdAlreadyExists(String id) {
        return new LocationAlreadyExistException(String.format(MESSAGE, id));
    }
}
