package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Especie;
import java.util.List;

public interface ListarEspeciesPorHabitatUseCase {

    List<Especie> execute(Integer idHabitat);
}
