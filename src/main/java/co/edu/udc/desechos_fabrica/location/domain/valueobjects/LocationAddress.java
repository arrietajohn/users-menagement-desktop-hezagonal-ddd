package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationAddressException;
import java.util.Objects;

public record LocationAddress (String address) {

    private static final int MINIMUM_LENGTH = 3;

    public LocationAddress {
        final String normalizedValue = Objects.requireNonNull(address, "Location address cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        address = normalizedValue;
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
        return address;
    }
}
