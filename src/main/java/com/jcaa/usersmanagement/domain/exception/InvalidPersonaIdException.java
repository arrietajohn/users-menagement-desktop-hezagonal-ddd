package com.jcaa.usersmanagement.domain.exception;

public final class InvalidPersonaIdException extends DomainException {

  private static final String MESSAGE_NULL = "The persona id must not be null.";
  private static final String MESSAGE_INVALID = "The persona id must be a positive integer.";

  private InvalidPersonaIdException(final String message) {
    super(message);
  }

  public static InvalidPersonaIdException becauseValueIsNull() {
    return new InvalidPersonaIdException(MESSAGE_NULL);
  }

  public static InvalidPersonaIdException becauseValueIsInvalid() {
    return new InvalidPersonaIdException(MESSAGE_INVALID);
  }
}
