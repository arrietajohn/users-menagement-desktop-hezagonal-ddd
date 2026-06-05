package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class InvalidEnterpriseIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "The location id must not be empty.";
    private static final String MESSAGE_INVALID_FORMAT = "The location id must contain only digits.";

    private InvalidEnterpriseIdException(final String message) {
        super(message);
    }

    public static InvalidEnterpriseIdException becauseValueIsEmpty() {
        return new InvalidEnterpriseIdException(MESSAGE_EMPTY);
    }

    public static InvalidEnterpriseIdException becauseInvalidFormat() {
        return new InvalidEnterpriseIdException(MESSAGE_INVALID_FORMAT);
    }
}
