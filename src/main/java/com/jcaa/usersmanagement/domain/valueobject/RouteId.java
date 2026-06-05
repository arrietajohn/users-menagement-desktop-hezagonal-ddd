package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public record RouteId(String value) {

    public RouteId {
        final String normalizedValue =
                Objects.requireNonNull(value, "RouteId cannot be null")
                        .trim();

        if (normalizedValue.isEmpty()) {
            throw new IllegalArgumentException("RouteId cannot be empty");
        }

        value = normalizedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}