package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllProgramasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllProgramasPort;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllProgramasService implements GetAllProgramasUseCase {

  private final GetAllProgramasPort getAllProgramasPort;

  @Override
  public List<ProgramaModel> execute() {
    return getAllProgramasPort.getAll();
  }
}
