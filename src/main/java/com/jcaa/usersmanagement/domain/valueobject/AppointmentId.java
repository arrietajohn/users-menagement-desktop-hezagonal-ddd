package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAppointmentIdException;

import java.util.Objects;

public record AppointmentId(String value) {

    public AppointmentId {
        final String normalizedValue = Objects.requireNonNull(value, "AppointmentId cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()){
            throw InvalidAppointmentIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {return value;}
}
