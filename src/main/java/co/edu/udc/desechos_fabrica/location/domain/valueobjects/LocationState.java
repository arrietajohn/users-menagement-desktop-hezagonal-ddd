package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationStateException;

import java.util.Objects;

public record LocationState(String state) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationState {
        final String normalizedValue = Objects.requireNonNull(state, "Location city can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        state = normalizedValue;
    }

    public void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationStateException.becauseValueIsEmpty();
        }
    }

    public void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidLocationStateException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return state;
    }
}
