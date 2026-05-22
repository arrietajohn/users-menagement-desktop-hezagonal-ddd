package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidAppointmentStatusException;

public enum AppointmentStatus {
    SCHEDULED,
    COMPLETED,
    CANCELED;

    public static AppointmentStatus fromString(final String value) {
        for (final AppointmentStatus status : values()){
            if (status.name().equalsIgnoreCase(value)){
                return status;
            }
        }
        throw InvalidAppointmentStatusException.becauseValueIsInvalid(value);
    }
}
