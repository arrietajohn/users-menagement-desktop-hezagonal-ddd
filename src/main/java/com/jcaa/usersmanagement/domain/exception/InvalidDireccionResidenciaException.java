package com.jcaa.usersmanagement.domain.exception;

public final class InvalidDireccionResidenciaException extends DomainException {

  private static final String MESSAGE_TOO_LONG =
      "The direccion de residencia must have at most %d characters.";

  private InvalidDireccionResidenciaException(final String message) {
    super(message);
  }

  public static InvalidDireccionResidenciaException becauseValueIsTooLong(final int maximumLength) {
    return new InvalidDireccionResidenciaException(String.format(MESSAGE_TOO_LONG, maximumLength));
  }
}
