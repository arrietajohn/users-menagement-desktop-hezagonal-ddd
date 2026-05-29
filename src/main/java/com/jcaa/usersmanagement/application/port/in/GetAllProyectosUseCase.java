package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import java.util.List;

public interface GetAllProyectosUseCase {
    List<Proyecto> execute();
}