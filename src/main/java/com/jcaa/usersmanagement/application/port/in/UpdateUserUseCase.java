package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateUserUseCase {
  UserModel execute(@NotNull @Valid UpdateUserCommand command);
}
