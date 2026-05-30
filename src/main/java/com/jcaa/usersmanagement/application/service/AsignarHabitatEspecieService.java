package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.AsignarHabitatEspecieUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.AsignarHabitatCommand;
import com.jcaa.usersmanagement.domain.model.Especie;

public class AsignarHabitatEspecieService implements AsignarHabitatEspecieUseCase {

    @Override
    public Especie execute(AsignarHabitatCommand command) {
        // Implementación pendiente - se conectará con el dominio
        System.out.println("Asignando hábitat " + command.idHabitat() + " a especie " + command.idEspecie());
        return null; // Temporal
    }
}
