package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import java.util.Objects;
import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationIdException;


public record LocationId(String value){

    public LocationId{
        final String normalizedValue = Objects.requireNonNull(value, "Location Id can not be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
