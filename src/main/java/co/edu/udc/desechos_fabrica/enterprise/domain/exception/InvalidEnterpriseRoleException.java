package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidEnterpriseRoleException extends DomainException {

    private static final String MESSAGE_INVALID = "The enterprise role '%s' is not valid.";

    private InvalidEnterpriseRoleException(final String message) {
        super(message);
    }

    public static InvalidEnterpriseRoleException becauseValueIsInvalid(final String role) {
        return new InvalidEnterpriseRoleException(String.format(MESSAGE_INVALID, role));
    }
}
