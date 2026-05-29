package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProgramaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProgramaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProgramaDesktopMapper {

  public CreateProgramaCommand toCreateCommand(final ProgramaRequest request) {
    return new CreateProgramaCommand(request.getId(), request.getNombre(), request.getGenero());
  }

  public UpdateProgramaCommand toUpdateCommand(final ProgramaRequest request) {
    return new UpdateProgramaCommand(request.getId(), request.getNombre(), request.getGenero());
  }

  public DeleteProgramaCommand toDeleteCommand(final Long id) {
    return new DeleteProgramaCommand(id);
  }

  public GetProgramaByIdQuery toGetByIdQuery(final Long id) {
    return new GetProgramaByIdQuery(id);
  }

  public ProgramaResponse toResponse(final ProgramaModel model) {
    return ProgramaResponse.builder()
        .id(model.getId().value())
        .nombre(model.getNombre().value())
        .genero(model.getGenero().value())
        .build();
  }

  public List<ProgramaResponse> toResponseList(final List<ProgramaModel> models) {
    return models.stream()
        .map(ProgramaDesktopMapper::toResponse)
        .collect(Collectors.toList());
  }
}
