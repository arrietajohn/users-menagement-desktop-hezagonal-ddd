package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSucursalCommand;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateSucursalUseCase {
    SucursalModel execute(@NotNull @Valid CreateSucursalCommand command);
}
