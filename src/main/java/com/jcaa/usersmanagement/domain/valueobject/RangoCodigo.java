package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidRangoCodigoException;
import java.util.Objects;

public record RangoCodigo(String value) {

    public RangoCodigo {
        final String normalizedValue =
                Objects.requireNonNull(value, "RangoCodigo cannot be null").trim().toUpperCase();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidRangoCodigoException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String value) {
        if (!value.matches("[A-Z0-9]{2,10}")) {
            throw InvalidRangoCodigoException.becauseFormatIsInvalid(value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
