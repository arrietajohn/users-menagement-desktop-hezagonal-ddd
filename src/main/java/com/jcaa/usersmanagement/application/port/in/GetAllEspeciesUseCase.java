package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Especie;
import java.util.List;

public interface GetAllEspeciesUseCase {
    List<Especie> execute();
}