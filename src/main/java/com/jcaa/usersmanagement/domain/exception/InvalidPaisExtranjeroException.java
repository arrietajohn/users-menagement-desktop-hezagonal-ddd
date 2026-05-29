package com.jcaa.usersmanagement.domain.exception;

public final class InvalidPaisExtranjeroException extends DomainException {

  private static final String MESSAGE_TOO_LONG =
      "The pais extranjero must have at most %d characters.";

  private InvalidPaisExtranjeroException(final String message) {
    super(message);
  }

  public static InvalidPaisExtranjeroException becauseValueIsTooLong(final int maximumLength) {
    return new InvalidPaisExtranjeroException(String.format(MESSAGE_TOO_LONG, maximumLength));
  }
}
