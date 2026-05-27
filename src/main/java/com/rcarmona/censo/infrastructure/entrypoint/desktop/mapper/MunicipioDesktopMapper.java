package com.rcarmona.censo.infrastructure.entrypoint.desktop.mapper;

import com.rcarmona.censo.application.municipio.service.dto.command.CreateMunicipioCommand;
import com.rcarmona.censo.application.municipio.service.dto.command.UpdateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.CreateMunicipioRequest;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.UpdateMunicipioRequest;

/**
 * Mapper de la capa de PresentaciÃ³n (Desktop).
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Componente traductor. Se encarga de mapear y transformar datos entre distintas capas (por ejemplo, entre Entidades de JPA a Modelos de Dominio, o de Dominio a DTOs) garantizando que ninguna capa se contamine con la tecnología de otra.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioDesktopMapper
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MunicipioDesktopMapper {

    private MunicipioDesktopMapper() {}

    public static CreateMunicipioCommand toCommand(CreateMunicipioRequest request) {
        return new CreateMunicipioCommand(
            request.nombre(),
            request.provinciaId()
        );
    }

    public static UpdateMunicipioCommand toCommand(UpdateMunicipioRequest request) {
        return new UpdateMunicipioCommand(
            request.id(),
            request.nombre(),
            request.provinciaId()
        );
    }

    public static MunicipioResponse toResponse(MunicipioModel model) {
        return new MunicipioResponse(
            model.getId().value(),
            model.getNombre(),
            model.getProvinciaId()
        );
    }
}


