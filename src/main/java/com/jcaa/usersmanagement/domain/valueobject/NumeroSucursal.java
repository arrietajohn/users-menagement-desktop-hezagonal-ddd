package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalNumberException;

import java.util.Objects;

public record NumeroSucursal(String value) {

    // Constructor compacto — aquí va la validación
    public NumeroSucursal {
        Objects.requireNonNull(value, "El número de sucursal no puede ser nulo");
        String normalized = value.trim();
        validateNotEmpty(normalized);
        validateFormat(normalized);
        value = normalized;
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidSucursalNumberException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(String value) {
        if (!value.matches("\\d+")) {
            throw InvalidSucursalNumberException.becauseValueIsInvalidFormat(value);
        }
    }

    @Override
    public String toString() { return value; }
}