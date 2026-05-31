package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRepresentanteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRepresentanteCommand;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public class RepresentanteApplicationMapper {

    public RepresentanteModel toDomain(CreateRepresentanteCommand command) {
        return new RepresentanteModel(
                command.id(),
                command.nombre(),
                command.telefono(),
                command.direccion()
        );
    }

    public void updateDomain(RepresentanteModel representante, UpdateRepresentanteCommand command) {
        representante.setNombre(command.nombre());
        representante.setTelefono(command.telefono());
        representante.setDireccion(command.direccion());
    }
}