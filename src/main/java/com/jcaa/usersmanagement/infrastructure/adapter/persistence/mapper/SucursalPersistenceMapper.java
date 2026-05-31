package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.BancoId;
import com.jcaa.usersmanagement.domain.valueobject.SucursalCityOperation;
import com.jcaa.usersmanagement.domain.valueobject.SucursalDirection;
import com.jcaa.usersmanagement.domain.valueobject.SucursalPostalCode;
import com.jcaa.usersmanagement.domain.valueobject.SucursalNumber;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.SucursalPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.SucursalEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SucursalPersistenceMapper {

    public SucursalPersistenceDto fromModelToDto(final SucursalModel sucursal) {
        return new SucursalPersistenceDto(
                sucursal.getId().value(),
                sucursal.getNumero().value(),
                sucursal.getDireccion().value(),
                sucursal.getCodigoPostal().value(),
                sucursal.getCiudad().value(),
                sucursal.getBancoId().value()
        );
    }

    public SucursalEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new SucursalEntity(
                resultSet.getString("id"),
                resultSet.getString("numero_sucursal"),
                resultSet.getString("direccion"),
                resultSet.getString("codigo_postal"),
                resultSet.getString("ciudad_operando"),
                resultSet.getString("id_banco")
        );
    }

    public SucursalModel fromEntityToModel(final SucursalEntity entity) {
        return new SucursalModel(
                new SucursaId(entity.id()),
                new SucursalNumber(entity.numero()),
                new SucursalDirection(entity.direccion()),
                new SucursalPostalCode(entity.codigoPostal()),
                new SucursalCityOperation(entity.ciudad()),
                new BancoId(entity.bancoId())
        );
    }

    public SucursalModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<SucursalModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<SucursalModel> sucursales = new ArrayList<>();
        while (resultSet.next()) {
            sucursales.add(fromResultSetToModel(resultSet));
        }
        return sucursales;
    }
}