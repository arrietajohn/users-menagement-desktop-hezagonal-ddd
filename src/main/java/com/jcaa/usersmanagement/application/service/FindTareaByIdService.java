package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.FindTareaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetTareaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.FindTareaByIdQuery;
import com.jcaa.usersmanagement.domain.exception.TareaNotFoundException;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindTareaByIdService implements FindTareaByIdUseCase {

  private final GetTareaByIdPort getTareaByIdPort;

  @Override
  public TareaModel execute(final FindTareaByIdQuery query) {
    return getTareaByIdPort.getById(new TareaId(query.id()))
        .orElseThrow(() -> TareaNotFoundException.becauseIdWasNotFound(query.id()));
  }
}
