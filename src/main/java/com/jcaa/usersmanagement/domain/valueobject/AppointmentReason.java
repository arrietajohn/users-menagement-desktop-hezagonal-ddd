package com.jcaa.usersmanagement.domain.valueobject;


import com.jcaa.usersmanagement.domain.exception.InvalidAppointmentReasonException;

import java.util.Objects;

public record AppointmentReason(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public AppointmentReason {
        final String normalizedValue = Objects.requireNonNull(value, "Appointment Reason value must not be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }
    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()){
            throw InvalidAppointmentReasonException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidAppointmentReasonException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {return value;}
}
