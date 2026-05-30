package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import com.jcaa.usersmanagement.domain.model.NombreCientifico;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EspeciePersistenceDto;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EspeciePersistenceMapper {

    public EspeciePersistenceDto fromModelToDto(final Especie especie) {
        return new EspeciePersistenceDto(
            especie.getId().getValor(),
            especie.getNombreCientifico().getValor(),
            especie.getNombreEspañol(),
            especie.getDescripcionGeneral(),
            null,
            null
        );
    }

    public Especie fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        EspecieId id = EspecieId.of(resultSet.getString("id"));
        NombreCientifico nombre = NombreCientifico.of(resultSet.getString("nombre_cientifico"));
        String nombreEspañol = resultSet.getString("nombre_español");
        String descripcion = resultSet.getString("descripcion");

        Especie especie = Especie.crear(id, nombre, nombreEspañol);
        if (descripcion != null && !descripcion.isBlank()) {
            especie.actualizarDescripcion(descripcion);
        }
        return especie;
    }

    public List<Especie> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<Especie> especies = new ArrayList<>();
        while (resultSet.next()) {
            especies.add(fromResultSetToModel(resultSet));
        }
        return especies;
    }
}
