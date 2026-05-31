package co.edu.udc.desechos_fabrica.user.application.service.mapper;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
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

  private static final String FIRST_NAME  = "John";
  private static final String LAST_NAME   = "Arrieta";
  private static final String EMAIL       = "john@example.com";
  private static final String PASSWORD    = "SecurePass1";
  private static final String ROLE        = "ADMIN";
  private static final String STATUS      = "ACTIVE";

  // ── fromCreateCommandToModel()

  @Test
  @DisplayName("fromCreateCommandToModel() mapea todos los campos y fija status PENDING")
  void shouldMapCreateCommandToModelWithPendingStatus() {
    // Arrange
    final CreateUserCommand command = new CreateUserCommand(FIRST_NAME,  LAST_NAME, EMAIL, PASSWORD, ROLE);

    // Act
    final UserModel result = UserApplicationMapper.fromCreateCommandToModel(command);

    // Assert
    assertAll(
        "fromCreateCommandToModel()",
        () -> assertEquals(FIRST_NAME,      result.getFirstName().value(),    "id"),
        () -> assertEquals(LAST_NAME,       result.getLastName().value(),  "name"),
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
    final UserModel currentUser = new UserModel(
            new UserFirstName(FIRST_NAME),
            new UserLastName(LAST_NAME),
            new UserEmail(EMAIL),
            currentPassword,
            UserRole.valueOf(ROLE),
            UserStatus.valueOf(STATUS));
    final UpdateUserCommand command =
        new UpdateUserCommand(EMAIL, FIRST_NAME, LAST_NAME, EMAIL, newPassword, ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentUser);

    // Assert
    assertAll(
        "fromUpdateCommandToModel() con nueva contraseña",
        () -> assertEquals(FIRST_NAME,      result.getFirstName().value(),    "firstName"),
        () -> assertEquals(LAST_NAME,       result.getLastName().value(),  "lastName"),
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
    final UserPassword currentPassword = UserPassword.fromPlainText(PASSWORD);
    final UserModel currentUser = new UserModel(
            new UserFirstName(FIRST_NAME),
            new UserLastName(LAST_NAME),
            new UserEmail(EMAIL),
            currentPassword,
            UserRole.valueOf(ROLE),
            UserStatus.valueOf(STATUS));
    final UpdateUserCommand command =
        new UpdateUserCommand(EMAIL, FIRST_NAME, LAST_NAME, EMAIL, null, ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentUser);

    // Assert
    assertSame(currentPassword, result.getPassword(),
        "debe conservar la instancia exacta de la contraseña actual");
  }

  // ── fromUpdateCommandToModel() — rama: contraseña blank → conserva la actual

  @Test
  @DisplayName("fromUpdateCommandToModel() conserva la contraseña actual cuando la nueva está en blanco")
  void shouldKeepCurrentPasswordWhenNewPasswordIsBlank() {
    final UserPassword currentPassword = UserPassword.fromPlainText(PASSWORD);
    final UserModel currentUser = new UserModel(
            new UserFirstName(FIRST_NAME),
            new UserLastName(LAST_NAME),
            new UserEmail(EMAIL),
            currentPassword,
            UserRole.valueOf(ROLE),
            UserStatus.valueOf(STATUS));
    final UpdateUserCommand command =
        new UpdateUserCommand(EMAIL, FIRST_NAME, LAST_NAME, EMAIL, "   ", ROLE, STATUS);

    // Act
    final UserModel result =
        UserApplicationMapper.fromUpdateCommandToModel(command, currentUser);

    // Assert
    assertSame(currentPassword, result.getPassword(),
        "debe conservar la instancia exacta de la contraseña actual");
  }

  // ── fromGetUserByEmailQueryToUserEmail()

  @Test
  @DisplayName("fromGetUserByEmailQueryToUserEmail() extrae el UserEmail del query")
  void shouldExtractUserEmailFromQuery() {
    // Arrange
    final GetUserByEmailQuery query = new GetUserByEmailQuery(EMAIL);

    // Act
    final UserEmail result = UserApplicationMapper.fromGetUserByEmailQueryToUserEmail(query);

    // Assert
    assertEquals(EMAIL, result.value(), "id debe coincidir con el del query");
  }

  // ── fromDeleteCommandToUserEmail()

  @Test
  @DisplayName("fromDeleteCommandToUserEmail() extrae el UserEmail del command")
  void shouldExtractUserEmailFromDeleteCommand() {
    // Arrange
    final DeleteUserCommand command = new DeleteUserCommand(EMAIL);

    // Act
    final UserEmail result = UserApplicationMapper.fromDeleteCommandToUserEmail(command);

    // Assert
    assertEquals(EMAIL, result.value(), "id debe coincidir con el del command");
  }
}
