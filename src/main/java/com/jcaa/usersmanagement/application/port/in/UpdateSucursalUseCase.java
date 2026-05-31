package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateSucursalCommand;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public interface UpdateSucursalUseCase {
    SucursalModel execute(@NotNull @Valid UpdateSucursalCommand command);
}
