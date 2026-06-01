package com.jcaa.usersmanagement.domain.valueobject.ofertaempleo;

import java.util.Objects;

public record EstadoOferta(String value) {

    public EstadoOferta {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Estado cannot be empty");
        }
    }
}