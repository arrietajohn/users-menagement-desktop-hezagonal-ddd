package com.jcaa.usersmanagement.domain.exception;

public final class TareaNotFoundException extends DomainException {

  private TareaNotFoundException(final String message) {
    super(message);
  }

  public static TareaNotFoundException becauseIdWasNotFound(final Long tareaId) {
    return new TareaNotFoundException(
        String.format("La tarea con id '%d' no fue encontrada.", tareaId));
  }
}
