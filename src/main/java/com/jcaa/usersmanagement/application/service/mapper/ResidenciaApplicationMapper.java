package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetResidenciaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.DireccionResidencia;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import com.jcaa.usersmanagement.domain.valueobject.PaisExtranjero;
import com.jcaa.usersmanagement.domain.valueobject.PersonaId;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResidenciaApplicationMapper {

  public ResidenciaModel fromCreateCommandToModel(final CreateResidenciaCommand command) {
    return ResidenciaModel.create(
        new PersonaId(command.personaId()),
        new MunicipioId(command.municipioId()),
        new PaisExtranjero(command.paisExtranjero()),
        new DireccionResidencia(command.direccion()),
        command.fechaInicio());
  }

  public ResidenciaModel fromUpdateCommandToModel(final UpdateResidenciaCommand command) {
    return new ResidenciaModel(
        new ResidenciaId(command.id()),
        new PersonaId(command.personaId()),
        new MunicipioId(command.municipioId()),
        new PaisExtranjero(command.paisExtranjero()),
        new DireccionResidencia(command.direccion()),
        command.fechaInicio());
  }

  public ResidenciaId fromGetResidenciaByIdQueryToResidenciaId(final GetResidenciaByIdQuery query) {
    return new ResidenciaId(query.id());
  }

  public ResidenciaId fromDeleteCommandToResidenciaId(final DeleteResidenciaCommand command) {
    return new ResidenciaId(command.id());
  }
}
