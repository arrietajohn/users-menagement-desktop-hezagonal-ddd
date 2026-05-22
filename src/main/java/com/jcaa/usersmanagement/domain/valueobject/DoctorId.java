package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidDoctorIdException;

import java.util.Objects;

public record DoctorId(String value) {

    public DoctorId {
        final String normalizedValue = Objects.requireNonNull(value, "DoctorId cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidDoctorIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {return value;}
}
