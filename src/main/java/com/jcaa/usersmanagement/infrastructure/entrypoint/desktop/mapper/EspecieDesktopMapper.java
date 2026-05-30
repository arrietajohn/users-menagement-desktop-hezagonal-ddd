package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EspecieDesktopMapper {

    public EspecieResponseDto toResponse(final Especie especie) {
        return new EspecieResponseDto(
            especie.getId().getValor(),
            especie.getNombreCientifico().getValor(),
            especie.getNombreEspañol(),
            especie.getDescripcionGeneral()
        );
    }
}
