package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteSucursalCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteSucursalUseCase {
    void execute(@NotNull @Valid DeleteSucursalCommand command);
}
