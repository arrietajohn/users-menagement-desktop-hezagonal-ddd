package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMunicipioIdException;
import java.util.Objects;

public record MunicipioId(String value) {

  private static final int MAX_LENGTH = 10;

  public MunicipioId {
    final String normalizedValue = Objects.requireNonNull(value, "MunicipioId cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidMunicipioIdException.becauseValueIsEmpty();
    }
  }

  private static void validateLength(final String normalizedValue) {
    if (normalizedValue.length() > MAX_LENGTH) {
      throw InvalidMunicipioIdException.becauseValueIsTooLong(MAX_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
