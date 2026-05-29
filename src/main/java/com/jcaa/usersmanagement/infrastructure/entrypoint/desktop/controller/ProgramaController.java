package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateProgramaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteProgramaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllProgramasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetProgramaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateProgramaUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ProgramaDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ProgramaController {

  private final CreateProgramaUseCase createProgramaUseCase;
  private final UpdateProgramaUseCase updateProgramaUseCase;
  private final DeleteProgramaUseCase deleteProgramaUseCase;
  private final GetProgramaByIdUseCase getProgramaByIdUseCase;
  private final GetAllProgramasUseCase getAllProgramasUseCase;

  public List<ProgramaResponse> listAllProgramas() {
    final var programas = getAllProgramasUseCase.execute();
    return ProgramaDesktopMapper.toResponseList(programas);
  }

  public ProgramaResponse findProgramaById(final Long id) {
    final var query = ProgramaDesktopMapper.toGetByIdQuery(id);
    final var programa = getProgramaByIdUseCase.execute(query);
    return ProgramaDesktopMapper.toResponse(programa);
  }

  public ProgramaResponse createPrograma(final ProgramaRequest request) {
    final var command = ProgramaDesktopMapper.toCreateCommand(request);
    final var programa = createProgramaUseCase.execute(command);
    return ProgramaDesktopMapper.toResponse(programa);
  }

  public ProgramaResponse updatePrograma(final ProgramaRequest request) {
    final var command = ProgramaDesktopMapper.toUpdateCommand(request);
    final var programa = updateProgramaUseCase.execute(command);
    return ProgramaDesktopMapper.toResponse(programa);
  }

  public void deletePrograma(final Long id) {
    final var command = ProgramaDesktopMapper.toDeleteCommand(id);
    deleteProgramaUseCase.execute(command);
  }
}
