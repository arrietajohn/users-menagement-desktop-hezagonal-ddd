package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListarEspeciesPorHabitatUseCase;
import com.jcaa.usersmanagement.domain.model.Especie;
import java.util.List;

public class ListarEspeciesPorHabitatService implements ListarEspeciesPorHabitatUseCase {

    @Override
    public List<Especie> execute(Integer idHabitat) {
        System.out.println("Listando especies del hábitat: " + idHabitat);
        return List.of(); // Temporal
    }
}
