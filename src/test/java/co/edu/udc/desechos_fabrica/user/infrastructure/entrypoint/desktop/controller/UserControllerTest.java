package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.user.application.port.in.CreateUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.DeleteUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.GetAllUsersUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.GetUserByEmailUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.LoginUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.in.UpdateUserUseCase;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.LoginCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidCredentialsException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for UserController.
 *
 * <p>Covers: correct delegation to every use-case port, accurate DTO→command/query mapping,
 * accurate domain-model→response mapping, and transparent exception propagation. All ports are
 * mocked; no infrastructure is exercised.
 */
@DisplayName("UserController")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private static final String BCRYPT_HASH =
      "$2a$12$abcdefghijklmnopqrstabcdefghijklmnñopqrstuvwxyzabcdefgh";

  @Mock private CreateUserUseCase createUserUseCase;
  @Mock private UpdateUserUseCase updateUserUseCase;
  @Mock private DeleteUserUseCase deleteUserUseCase;
  @Mock private GetUserByEmailUseCase getUserByEmailUseCase;
  @Mock private GetAllUsersUseCase getAllUsersUseCase;
  @Mock private LoginUseCase loginUseCase;

  private UserController controller;

  // ── helpers

  private static UserModel buildUser(
      final String firstName,
      final String lastName,
      final String email,
      final String nit,
      final UserRole role,
      final UserStatus status) {
    return new UserModel(
        new UserFirstName(firstName),
        new UserLastName(lastName),
        new UserEmail(email),
        new EnterpriseNit(nit),
        UserPassword.fromHash(BCRYPT_HASH),
        role,
        status);
  }

  @BeforeEach
  void setUp() {
    controller =
        new UserController(
            createUserUseCase,
            updateUserUseCase,
            deleteUserUseCase,
            getUserByEmailUseCase,
            getAllUsersUseCase,
            loginUseCase);
  }

  // ── listAllUsers

  @Test
  @DisplayName(
      "listAllUsers() returns a correctly mapped UserResponse list when the use case returns users")
  void listAllUsers_returnsMappedResponseList_whenUsersExist() {
    // Arrange
    final UserModel user =
        buildUser("Alice", "Smith", "alice@example.com", "1234567890", UserRole.ADMIN, UserStatus.ACTIVE);
    when(getAllUsersUseCase.execute()).thenReturn(List.of(user));

    // Act
    final List<UserResponse> result = controller.listAllUsers();

    // Assert
    assertAll(
        "single-user list mapping",
        () -> assertEquals(1, result.size(), "list must contain exactly one element"),
        () -> assertEquals("Alice", result.get(0).firstName(), "first name must match"),
        () -> assertEquals("Smith", result.get(0).lastName(), "last name must match"),
        () -> assertEquals("alice@example.com", result.get(0).email(), "email must match"),
        () -> assertEquals("ADMIN", result.get(0).role(), "role must match enum name"),
        () -> assertEquals("ACTIVE", result.get(0).status(), "status must match enum name"));
    verify(getAllUsersUseCase).execute();
  }

  @Test
  @DisplayName("listAllUsers() returns an empty list when the use case returns no users")
  void listAllUsers_returnsEmptyList_whenNoUsersExist() {
    // Arrange
    when(getAllUsersUseCase.execute()).thenReturn(List.of());

    // Act
    final List<UserResponse> result = controller.listAllUsers();

    // Assert
    assertTrue(result.isEmpty(), "result must be an empty list");
    verify(getAllUsersUseCase).execute();
  }

  // ── findUserByEmail

  @Test
  @DisplayName(
      "findUserByEmail() builds a GetUserByIdQuery with the given id and returns the mapped response")
  void findUserByEmail_returnsMappedResponse_whenUserExists() {
    // Arrange
    final String email = "bob@example.com";
    final UserModel user =
        buildUser("Bob", "Jones", email, "123456789", UserRole.MEMBER, UserStatus.ACTIVE);
    when(getUserByEmailUseCase.execute(any(GetUserByEmailQuery.class))).thenReturn(user);

    // Act
    final UserResponse result = controller.findUserByEmail(email);

    // Assert
    assertAll(
        "findUserByEmail response mapping",
        () -> assertEquals("Bob", result.firstName(), "first name must match"),
        () -> assertEquals("Jones", result.lastName(), "last name must match"),
        () -> assertEquals(email, result.email(), "email must match"),
        () -> assertEquals("MEMBER", result.role(), "role must match enum name"),
        () -> assertEquals("ACTIVE", result.status(), "status must match enum name"));
  }

  @Test
  @DisplayName(
      "findUserByEmail() propagates UserNotFoundException when the use case cannot find the user")
  void findUserByEmail_propagatesUserNotFoundException_whenUserDoesNotExist() {
    // Arrange
    final String email = "bob@example.com";
    when(getUserByEmailUseCase.execute(any(GetUserByEmailQuery.class)))
        .thenThrow(UserNotFoundException.becauseEmailWasNotFound(email));

    // Act & Assert
    assertThrows(
        UserNotFoundException.class,
        () -> controller.findUserByEmail(email),
        "UserNotFoundException must propagate without being wrapped");
  }

  // ── createUser

  @Test
  @DisplayName(
      "createUser() delegates a correctly populated CreateUserCommand and returns the mapped response")
  void createUser_delegatesCorrectCommandAndReturnsMappedResponse_whenCreationSucceeds() {
    // Arrange
    final CreateUserRequest request =
        new CreateUserRequest("Carol", "White", "carol@example.com", "Pass1234", UserRole.MEMBER);
    final UserModel createdUser =
        buildUser(
            "Carol", "White", "carol@example.com", "123456789", UserRole.MEMBER, UserStatus.PENDING);
    final ArgumentCaptor<CreateUserCommand> captor =
        ArgumentCaptor.forClass(CreateUserCommand.class);
    when(createUserUseCase.execute(captor.capture())).thenReturn(createdUser);

    // Act
    final UserResponse result = controller.createUser(request);

    // Assert
    assertAll(
        "createUser command delegation and response mapping",
        () ->
            assertEquals(
                "Carol", captor.getValue().firstName(), "command firstName name must match request first name"),
        () ->
            assertEquals(
                "White", captor.getValue().lastName(), "command lastName must match request last name"),
        () ->
            assertEquals(
                "carol@example.com",
                captor.getValue().email(),
                "command email must match request email"),
        () ->
            assertEquals(
                "Pass1234",
                captor.getValue().password(),
                "command password must match request password"),
        () ->
            assertEquals(
                "MEMBER", captor.getValue().role(), "command role must match request role"),
        () ->
            assertEquals(
                "PENDING",
                result.status(),
                "response status must reflect the domain model status"));
  }

  @Test
  @DisplayName(
      "createUser() propagates UserAlreadyExistsException when the use case rejects a duplicate email")
  void createUser_propagatesUserAlreadyExistsException_whenEmailIsDuplicated() {
    // Arrange
    final CreateUserRequest request =
        new CreateUserRequest("Dave", "Brown", "dave@example.com", "Pass5678", UserRole.MEMBER);
    when(createUserUseCase.execute(any()))
        .thenThrow(UserAlreadyExistsException.becauseEmailAlreadyExists("dave@example.com"));

    // Act & Assert
    assertThrows(
        UserAlreadyExistsException.class,
        () -> controller.createUser(request),
        "UserAlreadyExistsException must propagate without being wrapped");
  }

  // ── updateUser

  @Test
  @DisplayName(
      "updateUser() delegates a correctly populated UpdateUserCommand and returns the mapped response")
  void updateUser_delegatesCorrectCommandAndReturnsMappedResponse_whenUpdateSucceeds() {
    // Arrange
    final UpdateUserRequest request =
        new UpdateUserRequest(
                "admin@ecoresiduos.com","eve2@example.com", "Eve", "Martinez", "eve@example.com", "NewPass9!", "ADMIN", "ACTIVE", "123456789");
    final UserModel updatedUser =
        buildUser("Eve", "Martinez", "eve@example.com", "123456789", UserRole.ADMIN, UserStatus.ACTIVE);
    final ArgumentCaptor<UpdateUserCommand> captor =
        ArgumentCaptor.forClass(UpdateUserCommand.class);
    when(updateUserUseCase.execute(captor.capture())).thenReturn(updatedUser);

    // Act
    final UserResponse result = controller.updateUser(request);

    // Assert
    assertAll(
        "updateUser command delegation and response mapping",
        () ->
            assertEquals(
                "Eve", captor.getValue().newFirstName(), "command firstName must match request first name"),
        () ->
            assertEquals(
                "Martinez", captor.getValue().newLastName(), "command lastName must match request last name"),
        () ->
            assertEquals(
                "eve@example.com",
                captor.getValue().newEmail(),
                "command email must match request email"),
        () ->
            assertEquals(
                "NewPass9!",
                captor.getValue().password(),
                "command password must match request password"),
        () ->
            assertEquals("ADMIN", captor.getValue().role(), "command role must match request role"),
        () ->
            assertEquals(
                "ACTIVE", captor.getValue().status(), "command status must match request status"),
        () ->
            assertEquals(
                "ADMIN", result.role(), "response role must reflect the domain model role"));
  }

  @Test
  @DisplayName(
      "updateUser() propagates UserNotFoundException when the use case cannot find the user")
  void updateUser_propagatesUserNotFoundException_whenUserDoesNotExist() {
    // Arrange
    final UpdateUserRequest request =
        new UpdateUserRequest(
            "admin@ecoresiduos.com","ghost2@example.com","Ghost", "User", "ghost@example.com", "Pass9999!", "ADMIN", "INACTIVE", "123456789");
    when(updateUserUseCase.execute(any()))
        .thenThrow(UserNotFoundException.becauseEmailWasNotFound("ghost@example.com"));

    // Act & Assert
    assertThrows(
        UserNotFoundException.class,
        () -> controller.updateUser(request),
        "UserNotFoundException must propagate without being wrapped");
  }

  // ── deleteUser

  @Test
  @DisplayName("deleteUser() delegates a DeleteUserCommand with the given id to the use case")
  void deleteUser_delegatesDeleteCommandWithCorrectId() {
    // Arrange
    final ArgumentCaptor<DeleteUserCommand> captor =
        ArgumentCaptor.forClass(DeleteUserCommand.class);
    doNothing().when(deleteUserUseCase).execute(captor.capture());

    // Act
    controller.deleteUser("admin@ecoresiduos.com","ghost@example.com");

    // Assert
    assertEquals("ghost@example.com", captor.getValue().email(), "delete command email must match the provided email");
  }

  @Test
  @DisplayName(
      "deleteUser() propagates UserNotFoundException when the use case cannot find the user")
  void deleteUser_propagatesUserNotFoundException_whenUserDoesNotExist() {
    // Arrange
    doThrow(UserNotFoundException.becauseEmailWasNotFound("ghost@example.com"))
        .when(deleteUserUseCase)
        .execute(any());

    // Act & Assert
    assertThrows(
        UserNotFoundException.class,
        () -> controller.deleteUser("admin@ecoresiduos.com","ghost@example.com"),
        "UserNotFoundException must propagate without being wrapped");
  }

  // ── login

  @Test
  @DisplayName(
      "login() delegates a correctly populated LoginCommand and returns the mapped response")
  void login_delegatesCorrectCommandAndReturnsMappedResponse_whenCredentialsAreValid() {
    // Arrange
    final LoginRequest request = new LoginRequest("frank@example.com", "Pass1234!");
    final UserModel loggedUser =
        buildUser("Frank", "Green", "frank@example.com", "123456789", UserRole.MEMBER, UserStatus.ACTIVE);
    final ArgumentCaptor<LoginCommand> captor = ArgumentCaptor.forClass(LoginCommand.class);
    when(loginUseCase.execute(captor.capture())).thenReturn(loggedUser);

    // Act
    final UserResponse result = controller.login(request);

    // Assert
    assertAll(
        "login command delegation and response mapping",
        () ->
            assertEquals(
                "frank@example.com",
                captor.getValue().email(),
                "command email must match request email"),
        () ->
            assertEquals(
                "Pass1234!",
                captor.getValue().password(),
                "command password must match request password"),
        () ->
            assertEquals(
                "frank@example.com",
                result.email(),
                "response email must match the domain model email"),
        () ->
            assertEquals(
                "ACTIVE", result.status(), "response status must reflect the domain model status"));
  }

  @Test
  @DisplayName(
      "login() propagates InvalidCredentialsException when the use case rejects the credentials")
  void login_propagatesInvalidCredentialsException_whenCredentialsAreInvalid() {
    // Arrange
    final LoginRequest request = new LoginRequest("frank@example.com", "WrongPass1");
    when(loginUseCase.execute(any()))
        .thenThrow(InvalidCredentialsException.becauseCredentialsAreInvalid());

    // Act & Assert
    assertThrows(
        InvalidCredentialsException.class,
        () -> controller.login(request),
        "InvalidCredentialsException must propagate without being wrapped");
  }
}
