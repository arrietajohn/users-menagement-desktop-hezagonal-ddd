package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidResidenciaIdException;

public record ResidenciaId(Integer value) {

  public ResidenciaId {
    if (value == null) {
      throw InvalidResidenciaIdException.becauseValueIsNull();
    }
    if (value <= 0) {
      throw InvalidResidenciaIdException.becauseValueIsInvalid();
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
