package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.NinoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.NinoEntity;

public class NinoPersistenceMapper {

    public static NinoEntity toEntity(Nino nino) {
        return new NinoEntity(
                nino.getId(),
                nino.getMatricula().getValue(),
                nino.getNombreCompleto(),
                nino.getFechaNacimiento(),
                nino.getFechaIngreso(),
                nino.getFechaBaja(),
                null,
                nino.getEstadoInscripcion().name(),
                nino.getCreatedAt() != null ? nino.getCreatedAt().toString() : null,
                nino.getUpdatedAt() != null ? nino.getUpdatedAt().toString() : null
        );
    }
}
