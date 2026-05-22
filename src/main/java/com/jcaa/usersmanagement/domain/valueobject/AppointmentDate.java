package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAppointmentDateException;

import java.time.LocalDateTime;
import java.util.Objects;

public record AppointmentDate(LocalDateTime value) {

    public AppointmentDate {
        final LocalDateTime normalizedValue = Objects.requireNonNull(value, "AppointmentDate must not be null");
        validateNotPast(normalizedValue);
    }

    private static void validateNotPast(LocalDateTime normalizedValue) {
        if (normalizedValue.isBefore(LocalDateTime.now())) {
            throw InvalidAppointmentDateException.becauseDateIsInThePast();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
