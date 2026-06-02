package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEmpleadoCommand;
import com.jcaa.usersmanagement.domain.enums.EmpleadoEstado;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;

import java.time.LocalDate;

public final class EmpleadoApplicationMapper {

  private EmpleadoApplicationMapper() {}

  public static EmpleadoModel fromCreateCommandToModel(final CreateEmpleadoCommand command) {
    return EmpleadoModel.create(
        command.nombre(),
        command.apellido(),
        command.email(),
        command.cargo(),
        LocalDate.parse(command.fechaContratacion()),
        EmpleadoEstado.fromString(command.estado()));
  }

  public static EmpleadoModel fromUpdateCommandToModel(final UpdateEmpleadoCommand command) {
    return new EmpleadoModel(
        new EmpleadoId(command.id()),
        command.nombre(),
        command.apellido(),
        command.email(),
        command.cargo(),
        LocalDate.parse(command.fechaContratacion()),
        EmpleadoEstado.fromString(command.estado()));
  }
}
