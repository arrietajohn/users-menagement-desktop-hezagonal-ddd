package com.jcaa.usersmanagement.domain.exception;

public final class AuthorAlreadyExistsException extends DomainException {
    private AuthorAlreadyExistsException(final String message) {
        super(message);
    }

    public static AuthorAlreadyExistsException becauseEmailAlreadyExists(final String email) {
        return new AuthorAlreadyExistsException(String.format("An author with email '%s' already exists.", email));
    }
}