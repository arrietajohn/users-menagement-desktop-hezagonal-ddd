package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProgramaIdException;
import java.util.Objects;

public record ProgramaId(Long value) {

  public ProgramaId {
    Objects.requireNonNull(value, "ProgramaId cannot be null");
    if (value <= 0) {
      throw InvalidProgramaIdException.becauseValueIsInvalid();
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
