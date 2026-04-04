package com.jcaa.usersmanagement.application.service.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserApplicationMapper.
 *
 * <p>Cubre cada método público de conversión y las dos ramas del helper privado
 * {@code resolvePassword}: contraseña nueva presente y contraseña ausente (null / blank).
 */
@DisplayName("UserApplicationMapper")
class UserApplicationMapperTest {

  private static final String ID       = "u-001";
  private static final String NAME     = "John Arrieta";
  private static final String EMAIL    = "john@example.com";
  private static final String PASSWORD = "SecurePass1";
  private static final String ROLE     = "ADMIN";
  private static final String STATUS   = "ACTIVE";

  // ── fromCreateCommandToModel()

  @Test
  @DisplayName("fromCreateCommandToModel() mapea todos los campos y fija status PENDING")
  void shouldMapCreateCommandToModelWithPendingStatus() {
    // Arrange
    final CreateUserCommand command = new CreateUserCommand(ID, NAME, EMAIL, PASSWORD, ROLE);

    // Act
    final UserModel result = UserApplicationMapper.fromCreateCommandToModel(command);

    // Assert
    assertAll(
        "fromCreateCommandToModel()",
        () -> assertEquals(ID,              result.getId().value(),    "id"),
        () -> assertEquals(NAME,            result.getName().value(),  "name"),
        () -> assertEquals(EMAIL,           result.getEmail().value(), "email"),
        () -> assertEquals(UserRole.ADMIN,  result.getRole(),          "role"),
        () -> assertEquals(UserStatus.PENDING, result.getStatus(),     "status debe ser PENDING"),
        () -> assertTrue(result.getPassword().verifyPlain(PASSWORD),   "password debe ser verificable"));
  }

  // ── fromUpdateCommandToModel() — rama: nueva contraseña presente

  @Test
  @DisplayName("fromUpdateCommandToModel() usa la nueva contraseña cuando viene informada")
  void shouldUseNewPasswordWhenProvided() {
    // Arrange
    final String newPassword = "NuevoPass99";
    final UserPassword currentPassword = UserPassword.fromPlainText(PASSWORD);
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, NAME, EMAIL, newPassword, ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentPassword);

    // Assert
    assertAll(
        "fromUpdateCommandToModel() con nueva contraseña",
        () -> assertEquals(ID,              result.getId().value(),    "id"),
        () -> assertEquals(NAME,            result.getName().value(),  "name"),
        () -> assertEquals(EMAIL,           result.getEmail().value(), "email"),
        () -> assertEquals(UserRole.ADMIN,  result.getRole(),          "role"),
        () -> assertEquals(UserStatus.ACTIVE, result.getStatus(),      "status"),
        () -> assertTrue(result.getPassword().verifyPlain(newPassword), "debe usar la nueva contraseña"),
        () -> assertFalse(result.getPassword().verifyPlain(PASSWORD),   "no debe verificar la contraseña anterior"));
  }

  // ── fromUpdateCommandToModel() — rama: contraseña null → conserva la actual

  @Test
  @DisplayName("fromUpdateCommandToModel() conserva la contraseña actual cuando la nueva es null")
  void shouldKeepCurrentPasswordWhenNewPasswordIsNull() {
    // Arrange
    final UserPassword currentPassword = UserPassword.fromPlainText(PASSWORD);
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, NAME, EMAIL, null, ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentPassword);

    // Assert
    assertSame(currentPassword, result.getPassword(),
        "debe conservar la instancia exacta de la contraseña actual");
  }

  // ── fromUpdateCommandToModel() — rama: contraseña blank → conserva la actual

  @Test
  @DisplayName("fromUpdateCommandToModel() conserva la contraseña actual cuando la nueva está en blanco")
  void shouldKeepCurrentPasswordWhenNewPasswordIsBlank() {
    // Arrange
    final UserPassword currentPassword = UserPassword.fromPlainText(PASSWORD);
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, NAME, EMAIL, "   ", ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentPassword);

    // Assert
    assertSame(currentPassword, result.getPassword(),
        "debe conservar la instancia exacta de la contraseña actual");
  }

  // ── fromGetUserByIdQueryToUserId()

  @Test
  @DisplayName("fromGetUserByIdQueryToUserId() extrae el UserId del query")
  void shouldExtractUserIdFromQuery() {
    // Arrange
    final GetUserByIdQuery query = new GetUserByIdQuery(ID);

    // Act
    final UserId result = UserApplicationMapper.fromGetUserByIdQueryToUserId(query);

    // Assert
    assertEquals(ID, result.value(), "id debe coincidir con el del query");
  }

  // ── fromDeleteCommandToUserId()

  @Test
  @DisplayName("fromDeleteCommandToUserId() extrae el UserId del command")
  void shouldExtractUserIdFromDeleteCommand() {
    // Arrange
    final DeleteUserCommand command = new DeleteUserCommand(ID);

    // Act
    final UserId result = UserApplicationMapper.fromDeleteCommandToUserId(command);

    // Assert
    assertEquals(ID, result.value(), "id debe coincidir con el del command");
  }
}

