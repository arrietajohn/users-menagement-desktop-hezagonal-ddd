package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSalaIDException;

import java.util.Objects;

public record SalaID (String value) {

    public SalaID {
        final String normalizedValue = Objects.requireNonNull(value, "SalaId cannot be null").trim();
        validateNotEmpty(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidSalaIDException.becauseValueIsEmpty();
        }
    }
    @Override
    public String toString() {
        return value;
    }
}