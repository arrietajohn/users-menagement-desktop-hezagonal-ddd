package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaNombreException;
import java.util.Objects;

public record AsignaturaNombreCompleto(String value) {

  private static final int MINIMUM_LENGTH = 5;

  public AsignaturaNombreCompleto {
    final String normalized = Objects.requireNonNull(value, "AsignaturaNombreCompleto cannot be null").trim();
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

