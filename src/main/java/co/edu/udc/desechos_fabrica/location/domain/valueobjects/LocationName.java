package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import java.util.Objects;

public record LocationName(String locationName) {

    private static final int MINIMUN_LENGHT = 3;

    public LocationName {
        final String normalizedValue = Objects.requireNonNull(locationName, "Location name can not be null").trim();
    }

    @Override
    public String toString() {
        return locationName;
    }
}
