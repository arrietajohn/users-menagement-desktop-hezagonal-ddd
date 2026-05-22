package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAppointmentIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "the appointment id must not be empty.";

    private InvalidAppointmentIdException(final String message) {super(message);}

    public static InvalidAppointmentIdException becauseValueIsEmpty() {
        return new InvalidAppointmentIdException(MESSAGE_EMPTY);
    }
}
