package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public record RangoDescripcion(String value) {

    public RangoDescripcion {
        value = Objects.requireNonNullElse(value, "").trim();
    }

    @Override
    public String toString() {
        return value;
    }
}
