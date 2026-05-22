package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAppointmentReasonException extends DomainException {

    private static final String MESSAGE_EMPTY = "the appointment reason must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The appointment reason must have at least %d characters.";

    private InvalidAppointmentReasonException(final String message) {super(message);}

    public static InvalidAppointmentReasonException becauseValueIsEmpty() {
        return new InvalidAppointmentReasonException(MESSAGE_EMPTY);
    }

    public static InvalidAppointmentReasonException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidAppointmentReasonException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }


}
