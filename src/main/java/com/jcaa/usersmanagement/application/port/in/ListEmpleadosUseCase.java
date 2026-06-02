package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

import java.util.List;

public interface ListEmpleadosUseCase {
  List<EmpleadoModel> execute();
}
