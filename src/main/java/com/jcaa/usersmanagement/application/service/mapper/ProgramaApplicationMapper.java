package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProgramaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaGenero;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProgramaApplicationMapper {

  public ProgramaModel fromCreateCommandToModel(final CreateProgramaCommand command) {
    return ProgramaModel.create(
        new ProgramaId(command.id()),
        new ProgramaName(command.nombre()),
        new ProgramaGenero(command.genero()));
  }

  public ProgramaModel fromUpdateCommandToModel(final UpdateProgramaCommand command) {
    return ProgramaModel.create(
        new ProgramaId(command.id()),
        new ProgramaName(command.nombre()),
        new ProgramaGenero(command.genero()));
  }

  public ProgramaId fromGetProgramaByIdQueryToProgramaId(final GetProgramaByIdQuery query) {
    return new ProgramaId(query.id());
  }

  public ProgramaId fromDeleteCommandToProgramaId(final DeleteProgramaCommand command) {
    return new ProgramaId(command.id());
  }
}
