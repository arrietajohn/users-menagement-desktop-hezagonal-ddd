package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAppointmentDateException extends DomainException {

  private static final String PAST_DATE = "the appointment date cannot be in the past.";

  private InvalidAppointmentDateException(final String message) {super(message);}

  public static InvalidAppointmentDateException becauseDateIsInThePast() {
    return new InvalidAppointmentDateException(PAST_DATE);
  }

}
