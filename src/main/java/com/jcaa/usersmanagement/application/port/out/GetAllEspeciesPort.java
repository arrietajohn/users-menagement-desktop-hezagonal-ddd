package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Especie;
import java.util.List;

public interface GetAllEspeciesPort {
    List<Especie> getAll();
}