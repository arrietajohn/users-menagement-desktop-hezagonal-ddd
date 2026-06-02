package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

import java.util.List;

public interface GetAllEmpleadosPort {
  List<EmpleadoModel> getAll();
}
