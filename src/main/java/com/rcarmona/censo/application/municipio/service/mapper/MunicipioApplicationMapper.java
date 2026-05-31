package com.rcarmona.censo.application.municipio.service.mapper;

import com.rcarmona.censo.application.municipio.service.dto.command.CreateMunicipioCommand;
import com.rcarmona.censo.application.municipio.service.dto.command.UpdateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;

/**
 * Mapper de la capa de aplicaciÃ³n.
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
 * - Nombre de Clase/Interface: MunicipioApplicationMapper
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MunicipioApplicationMapper {

    private MunicipioApplicationMapper() {}

    public static MunicipioModel toDomain(CreateMunicipioCommand command) {
        return new MunicipioModel(
            null,
            command.nombre(),
            command.provinciaId()
        );
    }

    public static MunicipioModel toDomain(UpdateMunicipioCommand command) {
        return new MunicipioModel(
            new MunicipioId(command.id()),
            command.nombre(),
            command.provinciaId()
        );
    }
}


