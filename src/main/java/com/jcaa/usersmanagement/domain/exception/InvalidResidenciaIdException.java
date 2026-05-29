package com.jcaa.usersmanagement.domain.exception;

public final class InvalidResidenciaIdException extends DomainException {

  private static final String MESSAGE_NULL = "The residencia id must not be null.";
  private static final String MESSAGE_INVALID = "The residencia id must be a positive integer.";

  private InvalidResidenciaIdException(final String message) {
    super(message);
  }

  public static InvalidResidenciaIdException becauseValueIsNull() {
    return new InvalidResidenciaIdException(MESSAGE_NULL);
  }

  public static InvalidResidenciaIdException becauseValueIsInvalid() {
    return new InvalidResidenciaIdException(MESSAGE_INVALID);
  }
}
