package com.jcaa.usersmanagement.domain.enums;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserStatusTest {

  // --- Happy path ---

  @Test
  @DisplayName("Should return correct UserStatus for valid input")
  void shouldReturnCorrectUserStatusForValidInput() {
    assertEquals(UserStatus.ACTIVE, UserStatus.fromString("ACTIVE"));
    assertEquals(UserStatus.INACTIVE, UserStatus.fromString("INACTIVE"));
    assertEquals(UserStatus.PENDING, UserStatus.fromString("PENDING"));
    assertEquals(UserStatus.BLOCKED, UserStatus.fromString("BLOCKED"));
  }

  // --- Flujo excepciones y ramas de validación ---
  @ParameterizedTest
  @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b", "INVALID_STATUS"})
  @DisplayName("Should throw IllegalArgumentException when input is empty or invalid")
  void shouldThrowIllegalArgumentExceptionWhenInputIsEmptyOrInvalid(final String input) {
    assertThrows(InvalidUserStatusException.class, () -> UserStatus.fromString(input));
  }
}
