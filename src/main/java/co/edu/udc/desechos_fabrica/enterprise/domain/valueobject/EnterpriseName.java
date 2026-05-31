package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNameException;
import java.util.Objects;

public record EnterpriseName(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public EnterpriseName {
        final String normalizedValue = Objects.requireNonNull(value, "Enterprise name cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidEnterpriseNameException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidEnterpriseNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
