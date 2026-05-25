package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProgramaGeneroException;
import java.util.Objects;

public record ProgramaGenero(String value) {

  public ProgramaGenero {
    final String normalizedValue = Objects.requireNonNull(value, "ProgramaGenero cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidProgramaGeneroException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
