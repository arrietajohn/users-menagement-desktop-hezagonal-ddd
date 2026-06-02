package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ProyectoModel;

import java.util.List;

public interface ListProyectosUseCase {
  List<ProyectoModel> execute();
}
