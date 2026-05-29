package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Proyecto;

public interface CreateProyectoUseCase {
    Proyecto execute(Proyecto proyecto);
}