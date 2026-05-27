package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidRangoNombreException;
import java.util.Objects;

public record RangoNombre(String value) {

    public RangoNombre {
        final String normalizedValue =
                Objects.requireNonNull(value, "RangoNombre cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidRangoNombreException.becauseValueIsEmpty();
        }
    }

    private static void validateMinLength(final String value) {
        if (value.length() < 3) {
            throw InvalidRangoNombreException.becauseValueIsTooShort(value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}