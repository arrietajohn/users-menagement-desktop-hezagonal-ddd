package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public record RouteName(String value) {

    public RouteName {
        final String normalizedValue =
                Objects.requireNonNull(value, "RouteName cannot be null")
                        .trim();

        if (normalizedValue.isEmpty()) {
            throw new IllegalArgumentException("RouteName cannot be empty");
        }

        value = normalizedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}