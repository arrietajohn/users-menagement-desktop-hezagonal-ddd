package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseIdException;

import java.util.Objects;

public record EnterpriseId(Long value) {

    public EnterpriseId{
        Objects.requireNonNull(value, "Location Id can not be null");
        validateGreaterThanZero(value);
    }

    private static void validateGreaterThanZero(final Long value) {
        if (value <= 0) {
            throw InvalidEnterpriseIdException.becauseInvalidFormat();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}