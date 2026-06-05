package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationNameException;

import java.util.Objects;

public record LocationName(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationName {
        final String normalizedValue = Objects.requireNonNull(value, "Location name can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    public void validateNotEmpty(final String normalizedValue){
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationNameException.becauseValueIsEmpty();
        }
    }

    public void validateMinimumLength(final String normalizedValue){
        if (normalizedValue.length() < MINIMUM_LENGTH){
            throw InvalidLocationNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
