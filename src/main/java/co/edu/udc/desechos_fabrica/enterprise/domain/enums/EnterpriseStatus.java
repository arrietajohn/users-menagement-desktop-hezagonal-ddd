package co.edu.udc.desechos_fabrica.enterprise.domain.enums;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseStatusException;
import lombok.Getter;

@Getter
public enum EnterpriseStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    BLOCKED;

    public static EnterpriseStatus fromString(final String value) {
        for (final EnterpriseStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw InvalidEnterpriseStatusException.becauseValueIsInvalid(value);
    }
}
