package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAsignaturaSemestreException;

public record AsignaturaSemestre(int value) {

  public AsignaturaSemestre {
    if (value <= 0 || value > 12) {
      throw InvalidAsignaturaSemestreException.becauseValueIsOutOfRange(value);
    }
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}

