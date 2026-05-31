package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.RepresentantePersistenceDto;

public class RepresentantePersistenceMapper {

    public RepresentanteModel toDomain(RepresentantePersistenceDto dto) {
        return new RepresentanteModel(
                dto.idRepresentante(),
                dto.nombre(),
                dto.telefono(),
                dto.direccion()
        );
    }

    public RepresentantePersistenceDto toDto(RepresentanteModel model) {
        return new RepresentantePersistenceDto(
                model.getId(),
                model.getNombre(),
                model.getTelefono(),
                model.getDireccion()
        );
    }
}