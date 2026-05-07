package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaNombreException;
import java.util.Objects;

public record AsignaturaNombre(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public AsignaturaNombre {
    final String normalized = Objects.requireNonNull(value, "AsignaturaNombre cannot be null").trim();
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

