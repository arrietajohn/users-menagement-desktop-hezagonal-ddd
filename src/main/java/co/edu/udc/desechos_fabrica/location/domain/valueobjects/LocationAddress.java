package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import java.util.Objects;

public record LocationAddress (String address) {

    public LocationAddress {
        final String normalizedValue = Objects.requireNonNull(address, "Location address cannot be null").trim();
        address = normalizedValue;
    }

    @Override
    public String toString() {
        return address;
    }
}
