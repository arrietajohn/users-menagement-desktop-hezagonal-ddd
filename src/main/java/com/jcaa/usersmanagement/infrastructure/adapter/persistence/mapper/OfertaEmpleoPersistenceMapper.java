package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;
import com.jcaa.usersmanagement.domain.valueobject.ofertaempleo.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.OfertaEmpleoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfertaEmpleoPersistenceMapper {

    public static OfertaEmpleoEntity fromModelToEntity(OfertaEmpleoModel model) {
        return new OfertaEmpleoEntity(
                model.getId().value(),
                model.getTitulo().value(),
                model.getDescripcion().value(),
                model.getEmpresa().value(),
                model.getUbicacion().value(),
                model.getSalario().value(),
                model.getEstado().value()
        );
    }

    public static OfertaEmpleoModel fromResultSetToModel(ResultSet rs) throws SQLException {
        return new OfertaEmpleoModel(
                new OfertaEmpleoId(rs.getString("id")),
                new Titulo(rs.getString("titulo")),
                new Descripcion(rs.getString("descripcion")),
                new Empresa(rs.getString("empresa")),
                new Ubicacion(rs.getString("ubicacion")),
                new Salario(rs.getBigDecimal("salario")),
                new EstadoOferta(rs.getString("estado"))
        );
    }

    public static List<OfertaEmpleoModel> fromResultSetToList(ResultSet rs) throws SQLException {
        List<OfertaEmpleoModel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fromResultSetToModel(rs));
        }
        return list;
    }
}