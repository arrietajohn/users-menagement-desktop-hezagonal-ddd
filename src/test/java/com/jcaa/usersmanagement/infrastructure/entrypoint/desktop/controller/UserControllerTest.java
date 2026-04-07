package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteUserCommand;
import com.jcaa.usersmanagement.application.service.dto.command.LoginCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
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
  @Mock private GetUserByIdUseCase getUserByIdUseCase;
  @Mock private GetAllUsersUseCase getAllUsersUseCase;
  @Mock private LoginUseCase loginUseCase;

  private UserController controller;

  // ── helpers

  private static UserModel buildUser(
      final String id,
      final String name,
      final String email,
      final UserRole role,
      final UserStatus status) {
    return new UserModel(
        new UserId(id),
        new UserName(name),
        new UserEmail(email),
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
            getUserByIdUseCase,
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
        buildUser("u-001", "Alice Smith", "alice@example.com", UserRole.ADMIN, UserStatus.ACTIVE);
    when(getAllUsersUseCase.execute()).thenReturn(List.of(user));

    // Act
    final List<UserResponse> result = controller.listAllUsers();

    // Assert
    assertAll(
        "single-user list mapping",
        () -> assertEquals(1, result.size(), "list must contain exactly one element"),
        () -> assertEquals("u-001", result.get(0).id(), "id must match"),
        () -> assertEquals("Alice Smith", result.get(0).name(), "name must match"),
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

  // ── findUserById

  @Test
  @DisplayName(
      "findUserById() builds a GetUserByIdQuery with the given id and returns the mapped response")
  void findUserById_returnsMappedResponse_whenUserExists() {
    // Arrange
    final UserModel user =
        buildUser("u-002", "Bob Jones", "bob@example.com", UserRole.MEMBER, UserStatus.ACTIVE);
    when(getUserByIdUseCase.execute(new GetUserByIdQuery("u-002"))).thenReturn(user);

    // Act
    final UserResponse result = controller.findUserById("u-002");

    // Assert
    assertAll(
        "findUserById response mapping",
        () -> assertEquals("u-002", result.id(), "id must match"),
        () -> assertEquals("Bob Jones", result.name(), "name must match"),
        () -> assertEquals("bob@example.com", result.email(), "email must match"),
        () -> assertEquals("MEMBER", result.role(), "role must match enum name"),
        () -> assertEquals("ACTIVE", result.status(), "status must match enum name"));
  }

  @Test
  @DisplayName(
      "findUserById() propagates UserNotFoundException when the use case cannot find the user")
  void findUserById_propagatesUserNotFoundException_whenUserDoesNotExist() {
    // Arrange
    when(getUserByIdUseCase.execute(new GetUserByIdQuery("u-999")))
        .thenThrow(UserNotFoundException.becauseIdWasNotFound("u-999"));

    // Act & Assert
    assertThrows(
        UserNotFoundException.class,
        () -> controller.findUserById("u-999"),
        "UserNotFoundException must propagate without being wrapped");
  }

  // ── createUser

  @Test
  @DisplayName(
      "createUser() delegates a correctly populated CreateUserCommand and returns the mapped response")
  void createUser_delegatesCorrectCommandAndReturnsMappedResponse_whenCreationSucceeds() {
    // Arrange
    final CreateUserRequest request =
        new CreateUserRequest("u-003", "Carol White", "carol@example.com", "Pass1234", "REVIEWER");
    final UserModel createdUser =
        buildUser(
            "u-003", "Carol White", "carol@example.com", UserRole.REVIEWER, UserStatus.PENDING);
    final ArgumentCaptor<CreateUserCommand> captor =
        ArgumentCaptor.forClass(CreateUserCommand.class);
    when(createUserUseCase.execute(captor.capture())).thenReturn(createdUser);

    // Act
    final UserResponse result = controller.createUser(request);

    // Assert
    assertAll(
        "createUser command delegation and response mapping",
        () -> assertEquals("u-003", captor.getValue().id(), "command id must match request id"),
        () ->
            assertEquals(
                "Carol White", captor.getValue().name(), "command name must match request name"),
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
                "REVIEWER", captor.getValue().role(), "command role must match request role"),
        () ->
            assertEquals(
                "u-003",
                result.id(),
                "response id must come from the domain model returned by use case"),
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
        new CreateUserRequest("u-004", "Dave Brown", "dave@example.com", "Pass5678", "MEMBER");
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
            "u-005", "Eve Martinez", "eve@example.com", "NewPass9!", "ADMIN", "ACTIVE");
    final UserModel updatedUser =
        buildUser("u-005", "Eve Martinez", "eve@example.com", UserRole.ADMIN, UserStatus.ACTIVE);
    final ArgumentCaptor<UpdateUserCommand> captor =
        ArgumentCaptor.forClass(UpdateUserCommand.class);
    when(updateUserUseCase.execute(captor.capture())).thenReturn(updatedUser);

    // Act
    final UserResponse result = controller.updateUser(request);

    // Assert
    assertAll(
        "updateUser command delegation and response mapping",
        () -> assertEquals("u-005", captor.getValue().id(), "command id must match request id"),
        () ->
            assertEquals(
                "Eve Martinez", captor.getValue().name(), "command name must match request name"),
        () ->
            assertEquals(
                "eve@example.com",
                captor.getValue().email(),
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
                "u-005",
                result.id(),
                "response id must come from the domain model returned by use case"),
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
            "u-999", "Ghost User", "ghost@example.com", "Pass9999!", "MEMBER", "INACTIVE");
    when(updateUserUseCase.execute(any()))
        .thenThrow(UserNotFoundException.becauseIdWasNotFound("u-999"));

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
    controller.deleteUser("u-006");

    // Assert
    assertEquals("u-006", captor.getValue().id(), "delete command id must match the provided id");
  }

  @Test
  @DisplayName(
      "deleteUser() propagates UserNotFoundException when the use case cannot find the user")
  void deleteUser_propagatesUserNotFoundException_whenUserDoesNotExist() {
    // Arrange
    doThrow(UserNotFoundException.becauseIdWasNotFound("u-999"))
        .when(deleteUserUseCase)
        .execute(any());

    // Act & Assert
    assertThrows(
        UserNotFoundException.class,
        () -> controller.deleteUser("u-999"),
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
        buildUser("u-007", "Frank Green", "frank@example.com", UserRole.MEMBER, UserStatus.ACTIVE);
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
                "u-007",
                result.id(),
                "response id must come from the domain model returned by use case"),
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
