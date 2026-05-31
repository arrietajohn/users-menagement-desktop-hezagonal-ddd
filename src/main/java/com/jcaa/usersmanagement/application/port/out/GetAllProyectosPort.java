package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ProyectoModel;

import java.util.List;

public interface GetAllProyectosPort {
  List<ProyectoModel> getAll();
}
