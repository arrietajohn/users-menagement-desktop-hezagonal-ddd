package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseIdException;

import java.util.Objects;

public record EnterpriseId(String value) {

    public EnterpriseId {
        final String enterpriseId = Objects.requireNonNull(value, "Enterprise Id can not be null").trim();
        validateNotEmpty(enterpriseId);
        validateFormat(enterpriseId);
        value = enterpriseId;
    }

    public void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidEnterpriseIdException.becauseValueIsEmpty();
        }
    }

    public void validateFormat(final String normalizedValue) {
        if (!normalizedValue.matches("^[0-9]+$")) {
            throw InvalidEnterpriseIdException.becauseInvalidFormat();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}