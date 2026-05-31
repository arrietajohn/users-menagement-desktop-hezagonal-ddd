package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalDirectionException;
import com.jcaa.usersmanagement.domain.exception.InvalidSucursalPostalCode;

import java.util.Objects;

public record SucursalPostalCode(String value) {
    public SucursalPostalCode{
        final String normalizedValue = Objects.requireNonNullElse(value, "SucursalPostalCode no puede ir vacío").toUpperCase();

        value = normalizedValue;

    }

    public static void InvalidSucursalPostalCode(final String normalizedValue) {
        if(normalizedValue.isEmpty()) {
            throw InvalidSucursalPostalCode.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {return  value;}
}
