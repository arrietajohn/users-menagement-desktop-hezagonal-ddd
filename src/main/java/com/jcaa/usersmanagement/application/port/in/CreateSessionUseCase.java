package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateSessionCommand;
import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateSessionUseCase {
    Session execute(@NotNull @Valid CreateSessionCommand command);
}
