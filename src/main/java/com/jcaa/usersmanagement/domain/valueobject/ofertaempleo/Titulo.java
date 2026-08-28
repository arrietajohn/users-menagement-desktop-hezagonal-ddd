package com.jcaa.usersmanagement.domain.valueobject.ofertaempleo;

import java.util.Objects;

public record Titulo(String value) {

    public Titulo {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Titulo cannot be empty");
        }
    }
}