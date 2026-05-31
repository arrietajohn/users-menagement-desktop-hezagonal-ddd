package com.rcarmona.censo.infrastructure.adapter.persistence.municipio.mapper;

import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import com.rcarmona.censo.infrastructure.adapter.persistence.municipio.entity.MunicipioEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

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
 * - Nombre de Clase/Interface: MunicipioPersistenceMapper
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MunicipioPersistenceMapper {

    private MunicipioPersistenceMapper() {}

    public static MunicipioModel toDomain(MunicipioEntity entity) {
        if (entity == null) return null;
        return new MunicipioModel(
            new MunicipioId(entity.getId()),
            entity.getNombre(),
            entity.getProvinciaId()
        );
    }

    public static MunicipioEntity toEntity(MunicipioModel model) {
        if (model == null) return null;
        return MunicipioEntity.builder()
            .id(model.getId() != null ? model.getId().value() : null)
            .nombre(model.getNombre())
            .provinciaId(model.getProvinciaId())
            .build();
    }

    public static MunicipioEntity extractFromResultSet(ResultSet rs) throws SQLException {
        return MunicipioEntity.builder()
            .id(rs.getInt("id_municipio"))
            .nombre(rs.getString("nombre_municipio"))
            .provinciaId(rs.getInt("id_provincia"))
            .build();
    }
}
