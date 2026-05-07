package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaNumeroCreditosException;

public record AsignaturaNumeroCreditos(int value) {

  public AsignaturaNumeroCreditos {
    if (value <= 0) {
      throw InvalidAsignaturaNumeroCreditosException.becauseValueIsNotPositive(value);
    }
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}

