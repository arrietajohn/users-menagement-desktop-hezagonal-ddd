package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaNombreException;
import java.util.Objects;

public record AsignaturaContenidoTematico(String value) {

  private static final int MINIMUM_LENGTH = 10;

  public AsignaturaContenidoTematico {
    final String normalized = Objects.requireNonNull(value, "AsignaturaContenidoTematico cannot be null").trim();
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

