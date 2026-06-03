package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationCityException;

import java.util.Objects;

public record LocationCity(String city) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationCity {
        final String normalizedValue = Objects.requireNonNull(city, "Location city can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        city = normalizedValue;
    }

    public void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationCityException.becauseValueIsEmpty();
        }
    }

    public void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidLocationCityException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return city;
    }
}
