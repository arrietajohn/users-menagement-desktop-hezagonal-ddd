package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import java.util.Objects;

public record EnterpriseId(String value) {

    public EnterpriseId {
        final String enterpriseId = Objects.requireNonNull(value, "Enterprise Id can not be null").trim();
        value = enterpriseId;
    }

    @Override
    public String toString() {
        return value;
    }
}