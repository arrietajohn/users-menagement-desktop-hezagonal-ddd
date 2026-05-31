package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalCityOperationException;

import java.util.Objects;

public record SucursalCityOperation(String value) {

    public SucursalCityOperation{
        final String normalizedValue = Objects.requireNonNull(value, "SucursalCityValue no puede ir vacía").trim();
        validateIsNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    public static void validateIsNotEmpty(final String normalizedValue){
    throw InvalidSucursalCityOperationException.becauseValueIsEmpty();
    }
}
