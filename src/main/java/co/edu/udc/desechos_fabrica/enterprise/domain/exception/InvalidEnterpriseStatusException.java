package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidEnterpriseStatusException extends DomainException {

    private static final String MESSAGE_INVALID = "The enterprise status '%s' is not valid.";

    private InvalidEnterpriseStatusException(final String message) {
        super(message);
    }

    public static InvalidEnterpriseStatusException becauseValueIsInvalid(final String status) {
        return new InvalidEnterpriseStatusException(String.format(MESSAGE_INVALID, status));
    }
}
