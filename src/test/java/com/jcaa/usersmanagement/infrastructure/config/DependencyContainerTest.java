package com.jcaa.usersmanagement.infrastructure.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for DependencyContainer.
 *
 * <p>Covers: full wiring succeeds and exposes a non-null UserController, the same UserController
 * instance is returned on every call (immutable composition graph), and a database connection
 * failure during construction propagates as PersistenceException. DriverManager is mocked
 * statically so no real database is required.
 */
@DisplayName("DependencyContainer")
@ExtendWith(MockitoExtension.class)
class DependencyContainerTest {

  @Mock private Connection mockConnection;

  // ── constructor + userController() — happy path

  @Test
  @DisplayName(
      "constructor wires all dependencies and userController() returns a non-null UserController")
  void shouldWireAllDependenciesAndExposeNonNullUserController() {
    // Arrange
    try (final MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
      mockedDriverManager
          .when(
              () ->
                  DriverManager.getConnection(
                      any(String.class), any(String.class), any(String.class)))
          .thenReturn(mockConnection);

      // Act
      final DependencyContainer container = new DependencyContainer();

      // Assert
      assertNotNull(
          container.userController(),
          "userController() must return a non-null UserController after successful construction");
    }
  }

  // ── userController() — immutable composition graph

  @Test
  @DisplayName("userController() returns the same UserController instance on every call")
  void shouldReturnTheSameUserControllerInstanceOnEveryCall() {
    // Arrange
    try (final MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
      mockedDriverManager
          .when(
              () ->
                  DriverManager.getConnection(
                      any(String.class), any(String.class), any(String.class)))
          .thenReturn(mockConnection);
      final DependencyContainer container = new DependencyContainer();

      // Act
      final UserController first = container.userController();
      final UserController second = container.userController();

      // Assert
      assertSame(first, second, "userController() must return the same instance on every call");
    }
  }

  // ── constructor — database failure → PersistenceException

  @Test
  @DisplayName("constructor propagates PersistenceException when the database connection fails")
  void shouldPropagatePersistenceExceptionWhenDatabaseConnectionFails() {
    // Arrange — exception built before the static mock to avoid intercepting DriverManager
    // internal calls during SQLException construction
    final SQLException cause = new SQLException("Connection refused");
    try (final MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
      mockedDriverManager
          .when(
              () ->
                  DriverManager.getConnection(
                      any(String.class), any(String.class), any(String.class)))
          .thenThrow(cause);

      // Act & Assert
      assertThrows(
          PersistenceException.class,
          DependencyContainer::new,
          "PersistenceException must propagate without wrapping when DriverManager throws SQLException");
    }
  }
}
