package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalNumberIdException;

import java.util.Objects;

public record SucursaId(String value) {
    public SucursaId{
        final String normalizedValue = Objects.requireNonNull(value, "SucursalId no puede ser nula").trim();
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidSucursalNumberIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() { return value; }
}
