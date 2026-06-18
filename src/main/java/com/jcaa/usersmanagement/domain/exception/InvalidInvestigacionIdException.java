package com.jcaa.usersmanagement.domain.exception;

public final class InvalidInvestigacionIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "The investigation id must not be empty.";

    private InvalidInvestigacionIdException(final String message) {
        super(message);
    }

    public static InvalidInvestigacionIdException becauseValueIsEmpty() {
        return new InvalidInvestigacionIdException(MESSAGE_EMPTY);
    }
}