package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPersonaIdException;

public record PersonaId(Integer value) {

  public PersonaId {
    if (value == null) {
      throw InvalidPersonaIdException.becauseValueIsNull();
    }
    if (value <= 0) {
      throw InvalidPersonaIdException.becauseValueIsInvalid();
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
