package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPatientIdException;

import java.util.Objects;

public record PatientId(String value) {
    public PatientId {
        final String normalizedValue = Objects.requireNonNull(value, "PatientId cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }
    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()){
            throw InvalidPatientIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {return value;}
}
