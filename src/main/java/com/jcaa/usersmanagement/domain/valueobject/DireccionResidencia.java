package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidDireccionResidenciaException;

public record DireccionResidencia(String value) {

  private static final int MAX_LENGTH = 150;

  public DireccionResidencia {
    if (value != null) {
      final String normalizedValue = value.trim();
      validateLength(normalizedValue);
      value = normalizedValue.isEmpty() ? null : normalizedValue;
    }
  }

  private static void validateLength(final String normalizedValue) {
    if (normalizedValue.length() > MAX_LENGTH) {
      throw InvalidDireccionResidenciaException.becauseValueIsTooLong(MAX_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
