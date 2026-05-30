package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.VehiculoPersistenceDto;
import lombok.experimental.UtilityClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class VehiculoPersistenceMapper {

    public VehiculoPersistenceDto fromModelToDto(final Vehiculomodel vehiculo) {
        return new VehiculoPersistenceDto(
                vehiculo.getIdBastidor(),
                vehiculo.getPrecio(),
                vehiculo.getCilindrada(),
                vehiculo.getPotencia(),
                vehiculo.getEstado(),
                vehiculo.getIdModelo(),
                vehiculo.getIdServicio()
        );
    }

    public Vehiculomodel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return new Vehiculomodel(
                rs.getInt("id_Bastidor"),
                rs.getBigDecimal("precio"),
                rs.getString("cilindrada"),
                rs.getString("potencia"),
                rs.getString("estado"),
                rs.getInt("id_modelo"),
                rs.getInt("id_servicio")
        );
    }

    public List<Vehiculomodel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<Vehiculomodel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(fromResultSetToModel(rs));
        }
        return lista;

    }

}
