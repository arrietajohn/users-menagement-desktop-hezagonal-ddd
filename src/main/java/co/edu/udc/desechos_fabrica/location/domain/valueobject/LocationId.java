package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import java.util.Objects;
import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationIdException;


public record LocationId(Long value){

    public LocationId{
        Objects.requireNonNull(value, "Location Id can not be null");
        validateGreaterThanZero(value);
    }

    private static void validateGreaterThanZero(final Long value) {
        if (value <= 0) {
            throw InvalidLocationIdException.becauseInvalidFormat();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
