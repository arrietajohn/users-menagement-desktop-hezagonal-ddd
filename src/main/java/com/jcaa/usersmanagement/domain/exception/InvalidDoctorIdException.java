package com.jcaa.usersmanagement.domain.exception;

public final class InvalidDoctorIdException extends DomainException {

  private static final String EMPTY_MESSAGE = "Doctor Id cannot be empty";

  private InvalidDoctorIdException(final String message) {super(message);}

  public static InvalidDoctorIdException becauseValueIsEmpty() {
    return new InvalidDoctorIdException(EMPTY_MESSAGE);
  }
}
