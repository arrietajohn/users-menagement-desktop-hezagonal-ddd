package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.DireccionResidencia;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import com.jcaa.usersmanagement.domain.valueobject.PaisExtranjero;
import com.jcaa.usersmanagement.domain.valueobject.PersonaId;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ResidenciaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ResidenciaEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ResidenciaPersistenceMapper {

    public ResidenciaPersistenceDto fromModelToDto(final ResidenciaModel model) {
        return new ResidenciaPersistenceDto(
                model.getId() != null ? model.getId().value() : null,
                model.getPersonaId().value(),
                model.getMunicipioId().value(),
                model.getPaisExtranjero() != null ? model.getPaisExtranjero().value() : null,
                model.getDireccion() != null ? model.getDireccion().value() : null,
                model.getFechaInicio() != null ? model.getFechaInicio().toString() : null);
    }

    public ResidenciaEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
        return new ResidenciaEntity(
                rs.getInt("id"),
                rs.getInt("persona_id"),
                rs.getString("municipio_id"),
                rs.getString("pais_extranjero"),
                rs.getString("direccion"),
                rs.getString("fecha_inicio"));
    }

    public ResidenciaModel fromEntityToModel(final ResidenciaEntity entity) {
        return new ResidenciaModel(
                new ResidenciaId(entity.id()),
                new PersonaId(entity.personaId()),
                new MunicipioId(entity.municipioId()),
                entity.paisExtranjero() != null ? new PaisExtranjero(entity.paisExtranjero()) : null,
                entity.direccion() != null ? new DireccionResidencia(entity.direccion()) : null,
                entity.fechaInicio() != null ? LocalDate.parse(entity.fechaInicio()) : null);
    }

    public ResidenciaModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(rs));
    }

    public List<ResidenciaModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<ResidenciaModel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fromResultSetToModel(rs));
        }
        return list;
    }
}