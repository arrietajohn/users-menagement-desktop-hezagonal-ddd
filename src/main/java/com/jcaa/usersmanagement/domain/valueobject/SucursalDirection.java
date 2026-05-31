package com.jcaa.usersmanagement.domain.valueobject;


import com.jcaa.usersmanagement.domain.exception.InvalidSucursalDirectionException;

import java.util.Objects;

public record SucursalDirection(String value) {

    public SucursalDirection {
        final String normalizedValue = Objects.requireNonNull(value, "SucursalDirection no puede ser nula").trim();
        value = normalizedValue;
    }

    public static void validateIsNotEmpty(final String normalizedValue) {
        if(normalizedValue.isEmpty()) {
            throw InvalidSucursalDirectionException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {return value;}
}
