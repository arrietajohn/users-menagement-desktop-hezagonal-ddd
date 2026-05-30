package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidNumberSucursalException;
import com.jcaa.usersmanagement.domain.exception.InvalidUserEmailException;

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
            throw InvalidUserEmailException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(String value) {
        if (!value.matches("\\d+")) {
            throw InvalidUserEmailException.becauseFormatIsInvalid(value);
        }
    }

    @Override
    public String toString() { return value; }
}