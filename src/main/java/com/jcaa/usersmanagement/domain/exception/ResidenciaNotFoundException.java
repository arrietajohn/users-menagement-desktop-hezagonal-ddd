package com.jcaa.usersmanagement.domain.exception;

public final class ResidenciaNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The residencia with id '%s' was not found.";

  private ResidenciaNotFoundException(final String message) {
    super(message);
  }

  public static ResidenciaNotFoundException becauseIdWasNotFound(final Integer residenciaId) {
    return new ResidenciaNotFoundException(String.format(MESSAGE_BY_ID, residenciaId));
  }
}
