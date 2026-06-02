package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListTareasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllTareasPort;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListTareasService implements ListTareasUseCase {

  private final GetAllTareasPort getAllTareasPort;

  @Override
  public List<TareaModel> execute() {
    return getAllTareasPort.getAll();
  }
}
