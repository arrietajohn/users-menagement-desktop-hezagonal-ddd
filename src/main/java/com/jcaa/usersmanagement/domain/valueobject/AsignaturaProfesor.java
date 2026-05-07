package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaNombreException;
import java.util.Objects;

public record AsignaturaProfesor(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public AsignaturaProfesor {
    final String normalized = Objects.requireNonNull(value, "AsignaturaProfesor cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidAsignaturaNombreException.becauseValueIsEmpty();
    }
    if (normalized.length() < MINIMUM_LENGTH) {
      throw InvalidAsignaturaNombreException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}

