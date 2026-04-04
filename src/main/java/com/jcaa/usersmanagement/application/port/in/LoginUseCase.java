package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.LoginCommand;
import com.jcaa.usersmanagement.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface LoginUseCase {
  UserModel execute(@NotNull @Valid LoginCommand command);
}
