package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserIdTest {

  // --- Happy path ---

  @ParameterizedTest
  @ValueSource(strings = {" user123 ", "  user123  ", "user123\t"})
  @DisplayName("Should create UserId with trimmed value")
  void shouldCreateUserIdWithTrimmedValue(String input) {
    // Arrange
    final String correctUserId = "user123";
    // Act
    final UserId userId = new UserId(input);
    // Assert
    assertEquals(correctUserId, userId.toString());
  }

  // --- Flujo con exepciones y ramas condiconales

  @Test
  @DisplayName("Should throw NullPointerException when UserId is null")
  void shouldThrowNullPointerExceptionWhenUserIdIsNull() {
    assertThrows(NullPointerException.class, () -> new UserId(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b"})
  void shouldThrowIllegalArgumentExceptionWhenUserIdIsEmpty(String input) {
    // Act & Assert
    assertThrows(InvalidUserIdException.class, () -> new UserId(input));
  }
}
