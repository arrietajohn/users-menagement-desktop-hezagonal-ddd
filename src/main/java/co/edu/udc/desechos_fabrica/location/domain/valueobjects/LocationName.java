package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationNameException;

import java.util.Objects;

public record LocationName(String locationName) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationName {
        final String normalizedValue = Objects.requireNonNull(locationName, "Location name can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        locationName = normalizedValue;
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
        return locationName;
    }
}
