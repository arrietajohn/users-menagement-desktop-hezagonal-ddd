package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;

public interface DeleteProyectoPort {
  void delete(ProyectoId id);
}
