package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public interface UpdateSucursalUseCase {
    void execute(@NotNull @Valid UpdateUserCommand command);
}
