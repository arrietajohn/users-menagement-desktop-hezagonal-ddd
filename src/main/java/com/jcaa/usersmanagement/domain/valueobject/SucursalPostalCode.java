package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalPostalCodeException;

import java.util.Objects;

public record SucursalPostalCode(String value) {
    public SucursalPostalCode {
        Objects.requireNonNull(value, "El código postal no puede ser nulo");
        final String normalizedValue = value.trim();
        validateIsNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateIsNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidSucursalPostalCodeException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String value) {
        if (!value.matches("\\d{6}")) {
            throw InvalidSucursalPostalCodeException.becauseFormatIsInvalid(value);
        }
    }

    @Override
    public String toString() { return value; }
}
