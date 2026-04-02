package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserStatusException extends DomainException {

  private static final String MESSAGE_INVALID = "The user status '%s' is not valid.";

  private InvalidUserStatusException(final String message) {
    super(message);
  }

  public static InvalidUserStatusException becauseValueIsInvalid(final String status) {
    return new InvalidUserStatusException(String.format(MESSAGE_INVALID, status));
  }
}
