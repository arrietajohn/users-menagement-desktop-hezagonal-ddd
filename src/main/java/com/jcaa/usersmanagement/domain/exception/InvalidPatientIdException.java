package com.jcaa.usersmanagement.domain.exception;

public final class InvalidPatientIdException extends DomainException {

    private static final String EMPTY_MESSAGE = "Patient Id cannot be empty";

    private InvalidPatientIdException(final String message) {super(message);}

    public static InvalidPatientIdException becauseValueIsEmpty() {
        return new InvalidPatientIdException(EMPTY_MESSAGE);
    }
}
