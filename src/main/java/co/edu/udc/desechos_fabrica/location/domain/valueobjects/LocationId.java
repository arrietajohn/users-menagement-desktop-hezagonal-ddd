package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import java.util.Objects;

public record LocationId (String value){

    public LocationId{
        final String normalizedValue = Objects.requireNonNull(value, "Location Id can not be null").trim();
    }

    @Override
    public String toString() {
        return value;
    }
}
