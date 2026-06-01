package com.jcaa.usersmanagement.domain.valueobject.ofertaempleo;

import java.math.BigDecimal;

public record Salario(BigDecimal value) {

    public Salario {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salario must be positive");
        }
    }
}