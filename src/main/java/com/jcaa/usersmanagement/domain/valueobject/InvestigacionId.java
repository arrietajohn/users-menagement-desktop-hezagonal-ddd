package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidInvestigacionIdException;
import com.jcaa.usersmanagement.domain.exception.InvalidSessionIdException;

import java.util.Objects;

public record InvestigacionId (String value) {

    public InvestigacionId {
        final String normalizedValue = Objects.requireNonNull(value, "InvestigacionId cannot be null").trim();
        validateNotEmpty(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidInvestigacionIdException.becauseValueIsEmpty();
        }
    }
    @Override
    public String toString() {
        return value;
    }
}
