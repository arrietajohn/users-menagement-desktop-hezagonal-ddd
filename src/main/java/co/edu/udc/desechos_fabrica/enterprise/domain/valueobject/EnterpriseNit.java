package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import java.util.Objects;
import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNitException;

public record EnterpriseNit (String value) {

    private static final int MINIMUM_LENGTH = 9;
    private static final int MAXIMUM_LENGTH = 12;


    public EnterpriseNit {
        final String normalizedValue = Objects.requireNonNull(value, "Enterprise Nit can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidEnterpriseNitException.becauseValueIsEmpty();
        }
    }

    private static void validateLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH || normalizedValue.length() > MAXIMUM_LENGTH) {
            throw InvalidEnterpriseNitException.becauseLengthIsInvalid(MINIMUM_LENGTH, MAXIMUM_LENGTH);
        }
    }
}
