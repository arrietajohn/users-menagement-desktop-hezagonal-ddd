package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final LoginUseCase loginUseCase;

  public List<UserResponse> listAllUsers() {
    final var users = getAllUsersUseCase.execute();
    return UserDesktopMapper.toResponseList(users);
  }

  public UserResponse findUserById(final String id) {
    final var query = UserDesktopMapper.toGetByIdQuery(id);
    final var user = getUserByIdUseCase.execute(query);
    return UserDesktopMapper.toResponse(user);
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

  public void deleteUser(final String id) {
    final var command = UserDesktopMapper.toDeleteCommand(id);
    deleteUserUseCase.execute(command);
  }

  public UserResponse login(final LoginRequest request) {
    final var command = UserDesktopMapper.toLoginCommand(request);
    final var user = loginUseCase.execute(command);
    return UserDesktopMapper.toResponse(user);
  }
}
