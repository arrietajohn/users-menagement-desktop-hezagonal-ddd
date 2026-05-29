package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPaisExtranjeroException;

public record PaisExtranjero(String value) {

  private static final int MAX_LENGTH = 100;

  public PaisExtranjero {
    if (value != null) {
      final String normalizedValue = value.trim();
      validateLength(normalizedValue);
      value = normalizedValue.isEmpty() ? null : normalizedValue;
    }
  }

  private static void validateLength(final String normalizedValue) {
    if (normalizedValue.length() > MAX_LENGTH) {
      throw InvalidPaisExtranjeroException.becauseValueIsTooLong(MAX_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
