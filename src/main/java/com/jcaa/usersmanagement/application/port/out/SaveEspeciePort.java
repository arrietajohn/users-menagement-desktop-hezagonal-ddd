package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Especie;

public interface SaveEspeciePort {
    Especie save(Especie especie);
}