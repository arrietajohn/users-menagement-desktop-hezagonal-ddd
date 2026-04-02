package com.jcaa.usersmanagement.domain.enums;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserRoleTest {

  // --- Happy path ---

  @Test
  @DisplayName("Should return correct UserRole for valid input")
  void shouldReturnCorrectUserRoleForValidInput() {
    assertEquals(UserRole.ADMIN, UserRole.fromString("ADMIN"));
    assertEquals(UserRole.MEMBER, UserRole.fromString("MEMBER"));
    assertEquals(UserRole.REVIEWER, UserRole.fromString("REVIEWER"));
  }

  // --- Flujo con excepciones y ramas de validación ---

  @ParameterizedTest
  @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b", "INVALID_ROLE"})
  @DisplayName("Should throw IllegalArgumentException when input is empty or invalid")
  void shouldThrowIllegalArgumentExceptionWhenInputIsEmptyOrInvalid(String input) {
    assertThrows(InvalidUserRoleException.class, () -> UserRole.fromString(input));
  }
}
