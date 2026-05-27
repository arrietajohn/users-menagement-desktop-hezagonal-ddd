package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.LineaMilitar;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.RangoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.domain.valueobject.RangoNombre;
import com.jcaa.usersmanagement.domain.valueobject.TiempoMinimoAscenso;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.RangoMilitarPersistenceDto;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RangoMilitarPersistenceMapper {

    public RangoMilitarPersistenceDto fromModelToDto(final RangoMilitarModel rango) {
        return new RangoMilitarPersistenceDto(
                rango.getId().value(),
                rango.getCodigo().value(),
                rango.getNombre().value(),
                rango.getDescripcion().value(),
                rango.getLineaMilitar().name(),
                rango.getTiempoMinimoAscenso().meses());
    }

    public RangoMilitarModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return new RangoMilitarModel(
                new RangoId(rs.getString("id")),
                new RangoCodigo(rs.getString("codigo")),
                new RangoNombre(rs.getString("nombre")),
                new RangoDescripcion(rs.getString("descripcion")),
                LineaMilitar.fromString(rs.getString("linea_militar")),
                new TiempoMinimoAscenso(rs.getInt("tiempo_minimo_ascenso_meses")));
    }

    public List<RangoMilitarModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<RangoMilitarModel> rangos = new ArrayList<>();
        while (rs.next()) {
            rangos.add(fromResultSetToModel(rs));
        }
        return rangos;
    }
}
