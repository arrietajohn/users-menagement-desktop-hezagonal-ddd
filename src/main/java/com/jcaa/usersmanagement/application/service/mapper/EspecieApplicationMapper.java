package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;

public class EspecieApplicationMapper {

    public static Especie toDomain(CreateEspecieCommand command) {
        Especie especie = Especie.crear(
            EspecieId.generar(),
            command.nombreCientifico(),
            command.nombreEspañol()
        );
        if (command.descripcion() != null && !command.descripcion().isBlank()) {
            especie.actualizarDescripcion(command.descripcion());
        }
        return especie;
    }
}
