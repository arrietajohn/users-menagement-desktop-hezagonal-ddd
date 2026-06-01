package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller;

import co.edu.udc.desechos_fabrica.user.application.port.in.*;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final GetUserByEmailUseCase getUserByEmailUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final LoginUseCase loginUseCase;

  public List<UserResponse> listAllUsers() {
    final var users = getAllUsersUseCase.execute();
    return UserDesktopMapper.toResponseList(users);
  }

  public UserResponse findUserByEmail(final String email) {
    final var query = UserDesktopMapper.toGetByEmailQuery(email);
    final var user = getUserByEmailUseCase.execute(query);
    return UserDesktopMapper.toResponse(user);
  }

  public UserModel findUserModelByEmail(final String email) {
    final var query = UserDesktopMapper.toGetByEmailQuery(email);
    return getUserByEmailUseCase.execute(query);
  }

  public UserResponse createUser(final CreateUserRequest request) {
    final var command = UserDesktopMapper.toCreateCommand(request);
    final var user = createUserUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }

  public UserResponse updateUser(final UpdateUserRequest request) {
    final var command = UserDesktopMapper.toUpdateCommand(request);
    final var user = updateUserUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }

  public void deleteUser(final String actorEmail, final String targetEmail) {
    final var command = UserDesktopMapper.toDeleteCommand(actorEmail, targetEmail);
    deleteUserUseCase.execute(command);
  }

  public UserResponse login(final LoginRequest request) {
    final var command = UserDesktopMapper.toLoginCommand(request);
    final var user = loginUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }
}
