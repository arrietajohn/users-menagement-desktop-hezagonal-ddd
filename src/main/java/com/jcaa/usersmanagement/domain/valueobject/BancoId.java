package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidIdBanco;

import java.util.Objects;

public record BancoId(String value) {

    public BancoId {
        final String normalizeValue = Objects.requireNonNull(value, "BancoId no puede ir vacío").trim();

        value = normalizeValue;
    }

    public static void validateIsNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidIdBanco.becauseValueIsEmpty();
        }
    }
}
