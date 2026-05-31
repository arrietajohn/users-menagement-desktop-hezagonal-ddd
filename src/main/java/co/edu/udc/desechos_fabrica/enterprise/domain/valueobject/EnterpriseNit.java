package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import java.util.Objects;

public record EnterpriseNit (String value) {

    private static final int MINIMUN_LENGTH = 9;

    public EnterpriseNit {
        final String normalizedValue = Objects.requireNonNull(value, "Enterprise Nit can not be null").trim();
        value = normalizedValue;
    }
}
