package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import java.util.Objects;
import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationCountryException;

public record LocationCountry(String country) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationCountry {
        final String normalizedValue = Objects.requireNonNull(country, "Location country can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        country = normalizedValue;
    }

    public void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationCountryException.becauseValueIsEmpty();
        }
    }

    public void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidLocationCountryException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return country;
    }
}
