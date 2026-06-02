package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateTareaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateTareaCommand;
import com.jcaa.usersmanagement.domain.enums.TareaEstado;
import com.jcaa.usersmanagement.domain.enums.TareaPrioridad;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;

import java.time.LocalDate;

public final class TareaApplicationMapper {

  private TareaApplicationMapper() {}

  public static TareaModel fromCreateCommandToModel(final CreateTareaCommand command) {
    return TareaModel.create(
        command.titulo(),
        command.descripcion(),
        TareaPrioridad.fromString(command.prioridad()),
        TareaEstado.fromString(command.estado()),
        command.fechaVencimiento() != null && !command.fechaVencimiento().isBlank()
            ? LocalDate.parse(command.fechaVencimiento())
            : null,
        new EmpleadoId(command.empleadoId()));
  }

  public static TareaModel fromUpdateCommandToModel(final UpdateTareaCommand command) {
    return new TareaModel(
        new TareaId(command.id()),
        command.titulo(),
        command.descripcion(),
        TareaPrioridad.fromString(command.prioridad()),
        TareaEstado.fromString(command.estado()),
        command.fechaVencimiento() != null && !command.fechaVencimiento().isBlank()
            ? LocalDate.parse(command.fechaVencimiento())
            : null,
        new EmpleadoId(command.empleadoId()));
  }
}
