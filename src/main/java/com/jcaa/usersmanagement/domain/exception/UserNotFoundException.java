package com.jcaa.usersmanagement.domain.exception;

public final class UserNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The user with id '%s' was not found.";

  private UserNotFoundException(final String message) {
    super(message);
  }

  public static UserNotFoundException becauseIdWasNotFound(final String id_cliente) {
    return new UserNotFoundException(String.format(MESSAGE_BY_ID, id_cliente));
  }
}
