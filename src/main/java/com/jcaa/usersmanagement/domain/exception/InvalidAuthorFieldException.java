package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAuthorFieldException extends DomainException {
    private InvalidAuthorFieldException(final String message) {
        super(message);
    }

    public static InvalidAuthorFieldException becauseEmpty(final String field) {
        return new InvalidAuthorFieldException(String.format("The %s must not be empty.", field));
    }

    public static InvalidAuthorFieldException becauseInvalidFormat(final String field, final String value) {
        return new InvalidAuthorFieldException(String.format("The %s format is invalid: '%s'.", field, value));
    }
}