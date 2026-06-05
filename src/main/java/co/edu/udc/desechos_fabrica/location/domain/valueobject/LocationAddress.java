package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationAddressException;
import java.util.Objects;

public record LocationAddress (String value) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationAddress {
        final String normalizedValue = Objects.requireNonNull(value, "Location address cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    public void validateNotEmpty(final String normalizedValue){
        if (normalizedValue.isEmpty()) {
            throw InvalidLocationAddressException.becauseValueIsEmpty();
        }
    }

    public void validateMinimumLength(final String normalizedValue){
        if (normalizedValue.length() < MINIMUM_LENGTH){
            throw InvalidLocationAddressException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
