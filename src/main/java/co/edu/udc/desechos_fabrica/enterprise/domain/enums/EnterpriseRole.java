package co.edu.udc.desechos_fabrica.enterprise.domain.enums;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseRoleException;
import lombok.Getter;

@Getter
public enum EnterpriseRole {
    PRODUCER(1),
    TRANSPORTER(2),
    RECEPTOR(3);

    private final int level;

    EnterpriseRole(final int level) {
        this.level = level;
    }

    public static EnterpriseRole fromString(final String value) {
        for (final EnterpriseRole role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw InvalidEnterpriseRoleException.becauseValueIsInvalid(value);
    }
}
