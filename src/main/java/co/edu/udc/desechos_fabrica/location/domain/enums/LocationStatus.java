package co.edu.udc.desechos_fabrica.location.domain.enums;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationStatusException;
import lombok.Getter;

@Getter
public enum LocationStatus {

    ACTIVE(1),
    INACTIVE(2);

    private final int level;

    LocationStatus(final int level) {
        this.level = level;
    }

    public static LocationStatus fromString(final String value) {
        for (final LocationStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw InvalidLocationStatusException.becauseValueIsInvalid(value);
    }
}
