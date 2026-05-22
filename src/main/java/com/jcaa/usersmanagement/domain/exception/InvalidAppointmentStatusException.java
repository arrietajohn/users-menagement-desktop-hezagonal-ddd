package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAppointmentStatusException extends DomainException {

    private static final String MESSAGE_INVALID = "The appointment status '%s' is not valid.";

    private InvalidAppointmentStatusException(final String message) {super(message);}

    public static InvalidAppointmentStatusException becauseValueIsInvalid(final String status) {
        return new InvalidAppointmentStatusException(String.format(MESSAGE_INVALID, status));
    }
}
