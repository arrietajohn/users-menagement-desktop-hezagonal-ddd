package com.jcaa.usersmanagement.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for ValidatorProvider.
 *
 * <p>Covers that {@code buildValidator()} returns a functional {@link Validator}: non-null, able to
 * detect violations on invalid beans, and able to pass valid beans.
 */
@DisplayName("ValidatorProvider")
class ValidatorProviderTest {

  /**
   * Minimal bean with a {@code @NotNull} constraint to exercise the Validator without coupling to
   * production classes.
   */
  private record ConstrainedBean(@NotNull String requiredField) {}

  // ── buildValidator()

  @Test
  @DisplayName("buildValidator() returns a non-null Validator instance")
  void shouldReturnNonNullValidator() {
    // Act
    final Validator validator = ValidatorProvider.buildValidator();

    // Assert
    assertNotNull(validator, "Validator must not be null");
  }

  @Test
  @DisplayName("buildValidator() returns a Validator that detects violations on an invalid bean")
  void shouldDetectViolationsOnInvalidBean() {
    // Arrange
    final Validator validator = ValidatorProvider.buildValidator();
    final ConstrainedBean invalidBean = new ConstrainedBean(null);

    // Act
    final Set<ConstraintViolation<ConstrainedBean>> violations = validator.validate(invalidBean);

    // Assert
    assertFalse(
        violations.isEmpty(), "must detect the @NotNull violation when requiredField is null");
  }

  @Test
  @DisplayName("buildValidator() returns a Validator that finds no violations on a valid bean")
  void shouldFindNoViolationsOnValidBean() {
    // Arrange
    final Validator validator = ValidatorProvider.buildValidator();
    final ConstrainedBean validBean = new ConstrainedBean("valid value");

    // Act
    final Set<ConstraintViolation<ConstrainedBean>> violations = validator.validate(validBean);

    // Assert
    assertTrue(
        violations.isEmpty(), "must find no violations when requiredField has a non-null value");
  }
}
