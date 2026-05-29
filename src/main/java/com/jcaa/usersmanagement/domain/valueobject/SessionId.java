package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidSessionIdException;

import java.util.Objects;

public record SessionId (String value) {

    public SessionId {
        final String normalizedValue = Objects.requireNonNull(value, "SessionId cannot be null").trim();
        validateNotEmpty(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidSessionIdException.becauseValueIsEmpty();
        }
}
    @Override
    public String toString() {
        return value;
    }
}

