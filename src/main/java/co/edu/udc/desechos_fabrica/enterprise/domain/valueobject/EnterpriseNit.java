package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;
import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNitException;

public record EnterpriseNit (String value) {

    private static final Pattern ENTERPRISE_PATTERN = Pattern.compile("^[0-9]{9,12}$");

    public EnterpriseNit {
        final String normalizedValue = Objects.requireNonNull(value, "Enterprise Nit can not be null").trim();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidEnterpriseNitException.becauseValueIsEmpty();
        }
    }

    private void validateFormat(final String normalizedValue){
        if (!ENTERPRISE_PATTERN.matcher(normalizedValue).matches()) {
            throw InvalidEnterpriseNitException.becauseFormatIsInvalid();
        }
    }
}
