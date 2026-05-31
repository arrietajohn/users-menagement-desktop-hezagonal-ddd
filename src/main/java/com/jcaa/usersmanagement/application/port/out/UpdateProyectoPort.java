package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ProyectoModel;

public interface UpdateProyectoPort {
  ProyectoModel update(ProyectoModel proyecto);
}
