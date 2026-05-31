package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public final class InvalidEnterpriseNameException extends DomainException {

     private static final String MESSAGE_EMPTY = "The enterprise name must not be empty.";
     private static final String MESSAGE_TOO_SHORT = "The user name must have at least %d characters.";

     private InvalidEnterpriseNameException(String message) {
        super(message);
    }

    public static InvalidEnterpriseNameException becauseValueIsEmpty() {
        return new InvalidEnterpriseNameException(MESSAGE_EMPTY);
    }

    public static InvalidEnterpriseNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidEnterpriseNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
