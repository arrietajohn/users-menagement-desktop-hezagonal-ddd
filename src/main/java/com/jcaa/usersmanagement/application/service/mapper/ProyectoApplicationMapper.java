package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProyectoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProyectoCommand;
import com.jcaa.usersmanagement.domain.enums.ProyectoEstado;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;

import java.time.LocalDate;

public final class ProyectoApplicationMapper {

  private ProyectoApplicationMapper() {}

  public static ProyectoModel fromCreateCommandToModel(final CreateProyectoCommand command) {
    return ProyectoModel.create(
        command.nombreClave(),
        command.denominacion(),
        LocalDate.parse(command.fechaInicio()),
        command.fechaFin() != null && !command.fechaFin().isBlank()
            ? LocalDate.parse(command.fechaFin())
            : null,
        ProyectoEstado.fromString(command.estado()),
        command.promotorId());
  }

  public static ProyectoModel fromUpdateCommandToModel(final UpdateProyectoCommand command) {
    return new ProyectoModel(
        new ProyectoId(command.id()),
        command.nombreClave(),
        command.denominacion(),
        LocalDate.parse(command.fechaInicio()),
        command.fechaFin() != null && !command.fechaFin().isBlank()
            ? LocalDate.parse(command.fechaFin())
            : null,
        ProyectoEstado.fromString(command.estado()),
        command.promotorId());
  }
}
