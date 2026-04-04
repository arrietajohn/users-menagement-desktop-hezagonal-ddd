package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteUserCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteUserUseCase {
  void execute(@NotNull @Valid DeleteUserCommand command);
}
