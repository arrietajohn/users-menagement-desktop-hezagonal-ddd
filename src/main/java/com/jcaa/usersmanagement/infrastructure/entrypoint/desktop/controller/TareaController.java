package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateTareaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteTareaUseCase;
import com.jcaa.usersmanagement.application.port.in.FindTareaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.ListTareasUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateTareaUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateTareaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.TareaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateTareaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.TareaDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class TareaController {

  private final CreateTareaUseCase createTareaUseCase;
  private final UpdateTareaUseCase updateTareaUseCase;
  private final DeleteTareaUseCase deleteTareaUseCase;
  private final FindTareaByIdUseCase findTareaByIdUseCase;
  private final ListTareasUseCase listTareasUseCase;

  public List<TareaResponse> listAllTareas() {
    return TareaDesktopMapper.toResponseList(listTareasUseCase.execute());
  }

  public TareaResponse findTareaById(final Long id) {
    return TareaDesktopMapper.toResponse(
        findTareaByIdUseCase.execute(TareaDesktopMapper.toFindByIdQuery(id)));
  }

  public TareaResponse createTarea(final CreateTareaRequest request) {
    return TareaDesktopMapper.toResponse(
        createTareaUseCase.execute(TareaDesktopMapper.toCreateCommand(request)));
  }

  public TareaResponse updateTarea(final UpdateTareaRequest request) {
    return TareaDesktopMapper.toResponse(
        updateTareaUseCase.execute(TareaDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteTarea(final Long id) {
    deleteTareaUseCase.execute(TareaDesktopMapper.toDeleteCommand(id));
  }
}
