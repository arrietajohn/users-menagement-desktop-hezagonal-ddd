package com.jcaa.usersmanagement.domain.valueobject.ofertaempleo;

import java.util.Objects;

public record OfertaEmpleoId(String value) {

    public OfertaEmpleoId {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("OfertaEmpleoId cannot be null or empty");
        }
    }
}