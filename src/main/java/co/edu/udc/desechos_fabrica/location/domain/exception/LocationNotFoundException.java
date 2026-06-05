package co.edu.udc.desechos_fabrica.location.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class LocationNotFoundException extends DomainException {

    private static final String MESSAGE = "Location with id '%s' was not found.";

    public LocationNotFoundException(String message) {
        super(message);
    }

    public static LocationNotFoundException becauseIdAlreadyExists(String id) {
        return new LocationNotFoundException(String.format(MESSAGE, id));
    }
}
